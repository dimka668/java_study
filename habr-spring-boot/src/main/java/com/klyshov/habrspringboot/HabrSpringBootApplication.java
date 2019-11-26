package com.klyshov.habrspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HabrSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(new Class<?>[] {HabrSpringBootApplication.class, JpaConfig.class, SecSecurityConfig.class}, args);	}
}
