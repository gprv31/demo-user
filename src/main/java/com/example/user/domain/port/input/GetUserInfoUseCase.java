package com.example.user.domain.port.input;

import com.example.user.entity.user.User;
import io.reactivex.Single;

public interface GetUserInfoUseCase {

  Single<User> getUserById(String uuid);
}
