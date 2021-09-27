package com.example.user.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ValidationErrors {
  EMPTY_VALUES("Request body must not be null"),
  EMAIL("Incorrect user email"),
  NAME("Incorrect user name"),
  PASSWORD("Incorrect user password: Password must be letters, at least one upper case and at least two numbers"),
  USER_NOT_FOUND("User not found");

  private String detail;
}
