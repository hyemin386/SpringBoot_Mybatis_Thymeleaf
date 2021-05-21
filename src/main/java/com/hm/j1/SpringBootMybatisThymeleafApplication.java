package com.hm.j1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringBootMybatisThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisThymeleafApplication.class, args);
	}

}
