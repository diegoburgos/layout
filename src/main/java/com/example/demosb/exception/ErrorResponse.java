package com.example.demosb.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse extends Exception {
  private final int code;
  private final String message;
}