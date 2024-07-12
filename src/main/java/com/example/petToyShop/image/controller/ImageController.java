package com.example.petToyShop.image.controller;

import com.example.petToyShop.image.vo.Image;
import com.example.petToyShop.image.service.ImageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Value("${upload-dir}")
    private String imageDir;

    // 이미지 저장
    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file,
                                              @RequestParam("info") String infoJson) {
        try {
            // JSON 데이터 파싱
            ObjectMapper objectMapper = new ObjectMapper();
            Image imageInfo = objectMapper.readValue(infoJson, Image.class);

            String fileTitle = imageInfo.getTitle();
            String fileDescription = imageInfo.getDescription();

            Path uploadPath = Paths.get(imageDir);

            // 디렉토리 존재 여부 확인 및 생성
            if (Files.notExists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 파일 이름 설정 (안전하게 처리)
            String fileName = generateFileName(file);
            String filePath = imageDir + "/" + fileName;
            Path path = Paths.get(filePath);

            // 파일 저장
            Files.write(path, file.getBytes());

            Image image = new Image();
            image.setName(fileName);
            image.setTitle(fileTitle);
            image.setDescription(fileDescription);
            image.setFile_path(filePath);
            imageService.uploadImage(image);

            return ResponseEntity.status(HttpStatus.OK).body("Image uploaded successfully: " + file.getOriginalFilename());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to upload image", e);
        }
    }

    // 모든 이미지 파일 이름 목록을 조회하는 엔드포인트
    @GetMapping("/list")
    public ResponseEntity<List<String>> getAllImages() {
        try {
            List<String> images = imageService.getAllImages();
            return ResponseEntity.ok(images);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 특정 이미지 파일을 다운로드하는 엔드포인트
    @GetMapping("/{fileName}")
    public void downloadImage(@PathVariable String fileName, HttpServletResponse response) throws IOException {
        Path imagePath = imageService.getImagePath(fileName);

        if (Files.exists(imagePath)) {
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);

            Files.copy(imagePath, response.getOutputStream());
            response.flushBuffer();
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
        }
    }

    @GetMapping("/download-all")
    public void downloadAllImages(HttpServletResponse response) throws IOException {
        // 압축 파일 이름 설정
        String zipFileName = "all-images.zip";
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + zipFileName + "\"");

        try (ZipOutputStream zos = new ZipOutputStream(response.getOutputStream())) {
            // 이미지 디렉토리 경로 설정
            Path imageDirPath = Paths.get(imageDir);

            // 디렉토리 내의 모든 파일에 대해 압축 파일에 추가
            Files.walk(imageDirPath)
                    .filter(Files::isRegularFile)
                    .forEach(filePath -> {
                        try {
                            // 압축 파일에 추가할 엔트리 생성
                            ZipEntry zipEntry = new ZipEntry(imageDirPath.relativize(filePath).toString());
                            zos.putNextEntry(zipEntry);

                            // 파일 복사
                            Files.copy(filePath, zos);

                            zos.closeEntry();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

            zos.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 파일 이름 생성 메서드: 현재 시각 + 일련번호
    private String generateFileName(MultipartFile file) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStamp = dateFormat.format(new Date());
        //String originalFilename = file.getOriginalFilename();
        String fileName = timeStamp;
        return fileName;
    }

}
