package com.example.demosb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @EnableSwagger3
public class DemoSbApplication {

	// http://localhost:8080/swagger-ui/index.html#/
	// http://localhost:8080/h2-console/
	public static void main(String[] args) {
		SpringApplication.run(DemoSbApplication.class, args);
	}
}
