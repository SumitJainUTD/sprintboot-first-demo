package com.sumit.demo_spring_gradle_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"controller","service","model"})
public class DemoSpringGradleRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringGradleRestApplication.class, args);
	}

}
