package com.example.petToyShop.Login.dao;

import com.example.petToyShop.Login.vo.LoginVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginDao {
    LoginVo testXML();
}
