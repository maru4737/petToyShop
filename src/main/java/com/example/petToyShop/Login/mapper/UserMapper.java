package com.example.petToyShop.Login.mapper;

import com.example.petToyShop.Login.vo.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    String getUserById(String id);

    int SignUp(User user);

    void updateUser(User user);

    void deleteUser(int id);

    User Login(User user);
}
