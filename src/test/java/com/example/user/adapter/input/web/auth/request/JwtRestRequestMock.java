package com.example.user.adapter.input.web.auth.request;

public class JwtRestRequestMock {

  public static JwtRestRequest getMock() {
    return JwtRestRequest.builder()
             .username("admin")
             .password("admin")
             .build();
  }
}
