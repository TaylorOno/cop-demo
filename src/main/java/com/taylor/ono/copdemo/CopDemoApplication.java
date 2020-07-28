package com.taylor.ono.copdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CopDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CopDemoApplication.class, args);
	}
}
