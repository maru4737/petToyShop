package com.example.petToyShop.Login.controller;

import com.example.petToyShop.Login.service.LoginService;
import com.example.petToyShop.Login.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/Login")
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping("/LoginPage")
    public ModelAndView home(ModelAndView model) {

        String inputDatd = "testId";

        LoginVo loginvo = loginService.test();

        model.addObject("loginvo", loginvo);
        model.setViewName("test");

        return model;
    }
}
