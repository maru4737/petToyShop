package com.example.petToyShop.Login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/Login")
@Controller
public class LoginController {
    @GetMapping("/LoginPage")
    public String home() {
        return "test";
    }
}
