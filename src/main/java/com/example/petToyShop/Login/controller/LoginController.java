package com.example.petToyShop.Login.controller;

import com.example.petToyShop.Login.service.UserService;
import com.example.petToyShop.Login.vo.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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

        return "/Login/Login";
    }

    @PostMapping("/Login")
    public String Login(HttpServletRequest request, HttpServletResponse response, User user, Model model) {
        System.out.println(user);
        User LoginUserVo = userService.Login(user);
        System.out.println(LoginUserVo);

        if(LoginUserVo.getId() != null){
            HttpSession loginSess = request.getSession(true);
            loginSess.setAttribute("user",LoginUserVo);
        }
        //System.out.println("Requested userId: " + userService.getUserById(user));

        return "index";
    }

    @GetMapping("/SignUpPage")
    public String SignUpPage() {

        return "/Login/SignUp";
    }

    @PostMapping("/SignUp")
    public String SignUp(HttpServletRequest request, HttpServletResponse response, User user, Model model) {

        int result = userService.SignUp(user);

        return "index";
    }

}
