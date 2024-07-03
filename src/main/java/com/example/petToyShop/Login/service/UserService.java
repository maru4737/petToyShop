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

    public User getUserById(String id) {
        User user = new User();

        return user;
    }

    @Transactional
    public int SignUp(User user) {

        return userMapper.SignUp(user);
    }

    @Transactional
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Transactional
    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }

    @Transactional
    public User Login(User user) {
        User loginUser = new User();
        loginUser = userMapper.Login(user);
        return loginUser;
    }
}
