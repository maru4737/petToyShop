package com.example.petToyShop.image.mapper;

import com.example.petToyShop.image.vo.Image;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageMapper {

    int uploadImage(Image image);
}
