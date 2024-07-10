package com.example.petToyShop.Login.mapper;

import com.example.petToyShop.Login.vo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    int SignUp(User user);

    User Login(User user);
}
