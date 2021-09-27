package com.example.user.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {
  ROLE_ADMIN("ADMIN"),
  ROLE_USER("USER");

  private String value;
}
