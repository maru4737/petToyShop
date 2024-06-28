package com.example.petToyShop.Login.controller;

import com.example.petToyShop.Login.mapper.UserMapper;
import com.example.petToyShop.Login.service.UserService;
import com.example.petToyShop.Login.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/Login")
@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/LoginPage")
    public String LoginPage() {

        return "Login";
    }

    @GetMapping("/test")
    public String testPage() {

        return "test";
    }

}
