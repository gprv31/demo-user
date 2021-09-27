package com.example.user.adapter.output.h2.converter.output;

import com.example.user.adapter.output.h2.entity.UserEntity;
import com.example.user.entity.user.User;

import java.util.function.Function;

public class UserConverter implements Function<UserEntity, User> {

  @Override
  public User apply(UserEntity userEntity) {
    if (userEntity == null) {
      return null;
    }
    return User.builder()
             .id(userEntity.getId().toString())
             .creationDate(userEntity.getCreationDate())
             .token(userEntity.getToken())
             .lastLogin(userEntity.getLastLogin())
             .updateDate(userEntity.getUpdateDate())
             .enabled(userEntity.getEnabled())
             .build();
  }
}
