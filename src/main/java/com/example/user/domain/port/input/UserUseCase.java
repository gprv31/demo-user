package com.example.user.domain.port.input;

import com.example.user.entity.user.User;
import com.example.user.entity.user.request.UserRequest;
import io.reactivex.Single;

public interface UserUseCase {

  Single<User> saveUser(UserRequest userRequest);
}
