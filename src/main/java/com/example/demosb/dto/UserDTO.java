package com.example.demosb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDTO {
	private final String firstname;
	private final String lastname;
	private final String password;
	private final String email;
}
