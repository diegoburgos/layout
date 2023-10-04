package com.example.demosb.api;

import com.example.demosb.dto.UserDTO;
import com.example.demosb.entity.User;
import com.example.demosb.exception.ErrorNewUser;
import com.example.demosb.exception.ErrorResponse;
import com.example.demosb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/user", produces = "application/json")
public class UserAPI {

	@Autowired
	UserService userService;

	@GetMapping("")
	@ResponseBody
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
	}

	@GetMapping("/mail/{mail}")
	@ResponseBody
	public ResponseEntity<User> getUser(@RequestParam String mail) {
		log.warn("getUser(): with param {}", mail);
		User user = userService.getUserRepository().findByEmail(mail);
		if (user == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
	}

	@PostMapping("")
	@ResponseBody
	public ResponseEntity<User> newUser(@RequestBody UserDTO user) {
		try {
			return new ResponseEntity<>(userService.add(user), HttpStatus.OK);
		} catch (ErrorNewUser e) {
			log.warn("newUser exception caught adding user:{}", user, e);
			return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
