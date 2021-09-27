package com.example.user.domain.interactor;

import com.example.user.domain.port.input.GetUserInfoUseCase;
import com.example.user.domain.port.output.UserPort;
import com.example.user.entity.user.User;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class GetUserInfoUseCaseImpl implements GetUserInfoUseCase {

  private final UserPort userPort;

  @Override
  public Single<User> getUserById(String uuid) {
    log.info("Starting GetUserInfoUseCaseImpl.getUserById method");
    return userPort.getUserById(uuid)
             .doOnSuccess(i -> log.info("Finished GetUserInfoUseCaseImpl.getUserById method"))
             .doOnError(e -> log.error("Finished GetUserInfoUseCaseImpl.getUserById method with error", e));
  }
}
