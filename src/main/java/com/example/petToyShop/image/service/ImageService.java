package com.example.petToyShop.image.service;

import com.example.petToyShop.image.mapper.ImageMapper;
import com.example.petToyShop.image.vo.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Value("${upload-dir}")
    private String imageDir;

    @Transactional
    public int uploadImage(Image image) {

        return imageMapper.uploadImage(image);
    }

    @Transactional
    public List<String> getAllImages() throws IOException {
        Path path = Paths.get(imageDir);
        List<String> images = new ArrayList<>();

        if (Files.exists(path) && Files.isDirectory(path)) {
            images = Files.walk(path)
                    .filter(Files::isRegularFile)
                    .map(p -> p.toFile().getName())
                    .collect(Collectors.toList());
        }

        return images;
    }

    public Path getImagePath(String fileName) {
        return Paths.get(imageDir, fileName);
    }

}
