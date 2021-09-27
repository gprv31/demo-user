package com.example.user.entity.user.request;

import java.util.Collections;

public class UserRequestMock {

  public static UserRequest getMock() {
    return UserRequest.builder()
             .name("Jose Luis")
             .email("mail@mail.com")
             .password("Password22")
             .token("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJleHAiOj")
             .phones(Collections.singletonList(PhoneMock.getMock()))
             .build();
  }
}
