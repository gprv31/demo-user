package com.example.user.application.security;

import com.example.user.application.exception.UserExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
  private static final long serialVersionUID = -7858869558953243875L;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
                       AuthenticationException authException) throws IOException {
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    UserExceptionResponse userExceptionResponse = new UserExceptionResponse();
    userExceptionResponse.setCode(HttpStatus.UNAUTHORIZED.value());
    userExceptionResponse.setMessage(authException.getMessage());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.getWriter().write(new ObjectMapper().writeValueAsString(userExceptionResponse));
    response.getWriter().flush();
  }
}
