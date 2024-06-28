package com.example.petToyShop.Login.controller;

import com.example.petToyShop.Login.service.UserService;
import com.example.petToyShop.Login.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
@RequestMapping("/Test")
public class TestController {

    private final UserService userService;

    @Autowired
    public TestController(UserService userService) {
        this.userService = userService;
    }

    /*@GetMapping("/userId")
    public String getUserById(@PathVariable("userId") String userId) {
        System.out.println("Requested userId: " + userId);
        return "test";
    }*/

    @GetMapping("/userId")
    public String testPage(String userId) {
        //System.out.println("Requested userId: " + userId);
        System.out.println("Requested userId: " + userService.getUserById(userId));

        return "test";
    }
}
