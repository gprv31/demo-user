package com.example.user.application.exception;


public class UserRestException extends RuntimeException {
  public UserRestException(String errorMessage, Throwable err) {
    super(errorMessage, err);
  }
}
