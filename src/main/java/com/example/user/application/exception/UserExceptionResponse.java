package com.example.user.application.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class UserExceptionResponse {
  private int code;
  private String message;
}
