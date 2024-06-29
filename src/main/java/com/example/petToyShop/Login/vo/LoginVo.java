package com.example.petToyShop.Login.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVo {

    private String memberId;
    private String memberPwd;
    private String memberName;
    private String REG_TM;

}
