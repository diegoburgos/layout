package com.example.demosb.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class HealthAPI {

	public static final String HEALTH_OK_MSG = "Health check ok";

	@RequestMapping(method = RequestMethod.GET, value = "/health")
	public ResponseEntity<String> addCloudManager(){
		return new ResponseEntity<>(HEALTH_OK_MSG, HttpStatus.OK);
	}
}
