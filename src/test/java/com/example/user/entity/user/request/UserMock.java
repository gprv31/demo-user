package com.example.user.entity.user.request;

import com.example.user.entity.user.User;

import java.time.LocalDateTime;

public class UserMock {

  public static User getMock() {
    return User.builder()
             .id("d11637-d201-4a9f-97fb-a5e362253400")
             .token("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJleHAiOj")
             .creationDate(LocalDateTime.now())
             .lastLogin(LocalDateTime.now())
             .enabled(Boolean.TRUE)
             .build();
  }
}
