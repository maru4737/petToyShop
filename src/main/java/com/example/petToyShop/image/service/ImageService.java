package com.example.petToyShop.image.service;

import com.example.petToyShop.image.mapper.ImageMapper;
import com.example.petToyShop.image.vo.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImageService {

    @Autowired
    private ImageMapper imageMapper;

    @Transactional
    public int uploadImage(Image image) {

        return imageMapper.uploadImage(image);
    }

}
