package com.userData.userData;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.userData.userData.client")
public class UserDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserDataApplication.class, args);
	}

}
