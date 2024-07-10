package com.example.petToyShop.Login.service;

import com.example.petToyShop.Login.mapper.UserMapper;
import com.example.petToyShop.Login.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public int SignUp(User user) {

        return userMapper.SignUp(user);
    }

    @Transactional
    public User Login(User user) {
        User loginUser = new User();
        loginUser = userMapper.Login(user);
        return loginUser;
    }
}
