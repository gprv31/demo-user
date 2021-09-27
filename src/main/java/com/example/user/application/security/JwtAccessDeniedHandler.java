package com.example.user.application.security;

import com.example.user.application.exception.UserExceptionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response,
                     AccessDeniedException accessDeniedException) throws IOException {
    UserExceptionResponse userExceptionResponse = new UserExceptionResponse();
    userExceptionResponse.setCode(HttpStatus.FORBIDDEN.value());
    userExceptionResponse.setMessage(accessDeniedException.getMessage());
    OutputStream out = response.getOutputStream();
    ObjectMapper mapper = new ObjectMapper();
    mapper.writeValue(out, userExceptionResponse);
    out.flush();
  }
}
