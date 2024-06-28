package com.example.petToyShop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan
@SpringBootApplication
public class PetToyShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetToyShopApplication.class, args);
	}

}
