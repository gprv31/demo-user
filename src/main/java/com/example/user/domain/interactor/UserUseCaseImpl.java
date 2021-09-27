package com.example.user.domain.interactor;

import com.example.user.domain.port.input.UserUseCase;
import com.example.user.domain.port.output.UserPort;
import com.example.user.entity.user.User;
import com.example.user.entity.user.request.UserRequest;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UserUseCaseImpl implements UserUseCase {

  private final UserPort userPort;

  @Override
  public Single<User> saveUser(UserRequest userRequest) {
    log.info("Starting UserUseCaseImpl.saveUser method");
    return userPort.saveUser(userRequest)
             .doOnSuccess(i -> log.info("Finished UserUseCaseImpl.saveUser method"))
             .doOnError(e -> log.error("Finished UserUseCaseImpl.saveUser method with error", e));
  }
}
