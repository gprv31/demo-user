package com.example.user.application.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@RestControllerAdvice
public class ErrorHandlingController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<UserExceptionResponse> generalException(Exception e) {
    e.printStackTrace();
    UserExceptionResponse userExceptionResponse = new UserExceptionResponse();
    userExceptionResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    userExceptionResponse.setMessage(e.getMessage());
    return new ResponseEntity<>(userExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(value = AccessDeniedException.class)
  public ResponseEntity<UserExceptionResponse> accessDeniedException(Exception e) {
    UserExceptionResponse userExceptionResponse = new UserExceptionResponse();
    userExceptionResponse.setCode(HttpStatus.UNAUTHORIZED.value());
    userExceptionResponse.setMessage(e.getMessage());
    return new ResponseEntity<>(userExceptionResponse, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(value = UserRestException.class)
  public ResponseEntity<UserExceptionResponse> userException(Exception e) {
    UserExceptionResponse userExceptionResponse = new UserExceptionResponse();
    userExceptionResponse.setCode(HttpStatus.BAD_REQUEST.value());
    userExceptionResponse.setMessage(e.getMessage());
    return new ResponseEntity<>(userExceptionResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = DataIntegrityViolationException.class)
  public ResponseEntity<UserExceptionResponse> dataIntegrationViolation(Exception e) {
    e.printStackTrace();
    UserExceptionResponse userExceptionResponse = new UserExceptionResponse();
    userExceptionResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
    userExceptionResponse.setMessage("Email already exists.");
    return new ResponseEntity<>(userExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
