package com.example.user.entity.user.request;

import com.example.user.entity.user.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
  private String name;
  private String email;
  private String password;
  private String token;
  private List<Phone> phones;
}
