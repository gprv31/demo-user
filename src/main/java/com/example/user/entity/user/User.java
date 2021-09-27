package com.example.user.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

  private String id;
//  private String username;
  private String token;
  private LocalDateTime creationDate;
  private LocalDateTime updateDate;
  private LocalDateTime lastLogin;
  private Boolean enabled;

}
