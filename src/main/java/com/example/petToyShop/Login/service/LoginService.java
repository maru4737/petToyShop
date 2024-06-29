package com.example.petToyShop.Login.service;

import com.example.petToyShop.Login.dao.LoginDao;
import com.example.petToyShop.Login.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginDao dao;

    public LoginVo test() {
        return dao.testXML();
    }

}
