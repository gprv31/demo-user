package com.example.user.adapter.input.web.user.model.converter.response;

import com.example.user.adapter.input.web.user.model.response.UserRestResponse;
import com.example.user.entity.user.User;
import com.example.user.util.DateUtils;

import java.util.function.Function;

public class UserRestResponseConverter implements Function<User, UserRestResponse> {

  @Override
  public UserRestResponse apply(User user) {
    if (user == null) {
      return null;
    }
    return UserRestResponse.builder()
             .id(user.getId())
             .created(DateUtils.format(user.getCreationDate(), DateUtils.REST_DATE_FORMATTER))
             .lastLogin(DateUtils.format(user.getLastLogin(), DateUtils.REST_DATE_FORMATTER))
             .modified(DateUtils.format(user.getUpdateDate(), DateUtils.REST_DATE_FORMATTER))
             .token(user.getToken())
             .active(user.getEnabled())
             .build();
  }
}
