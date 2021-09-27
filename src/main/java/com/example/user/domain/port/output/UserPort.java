package com.example.user.domain.port.output;

import com.example.user.entity.user.User;
import com.example.user.entity.user.request.UserRequest;
import io.reactivex.Single;

public interface UserPort {

  Single<User> saveUser(UserRequest userRequest);

  Single<User> getUserById(String uuid);

  Single<User> modifyUserById(String uuid, UserRequest userRequest);
}
