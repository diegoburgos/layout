package com.example.demosb.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorNewUser extends Exception {
	private final String message;
}
