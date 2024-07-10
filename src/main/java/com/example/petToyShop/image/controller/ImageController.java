package com.example.petToyShop.image.controller;

import com.example.petToyShop.image.vo.Image;
import com.example.petToyShop.image.service.ImageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Value("${file.upload-dir}")
    private String uploadDir;

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

            // 파일 업로드 및 저장
            String fileName = file.getOriginalFilename();
            String filePath = uploadDir + "/" + fileName;
            Path path = Paths.get(filePath);
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

}
