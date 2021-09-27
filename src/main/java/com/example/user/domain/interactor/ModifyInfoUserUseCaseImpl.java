package com.example.user.domain.interactor;

import com.example.user.domain.port.input.ModifyInfoUserUseCase;
import com.example.user.domain.port.output.UserPort;
import com.example.user.entity.user.User;
import com.example.user.entity.user.request.UserRequest;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ModifyInfoUserUseCaseImpl implements ModifyInfoUserUseCase {

  private final UserPort userPort;

  @Override
  public Single<User> modifyUserById(String uuid, UserRequest userRequest) {
    log.info("Starting ModifyInfoUserUseCaseImpl.modifyUserById method");
    return userPort.modifyUserById(uuid, userRequest)
             .doOnSuccess(i -> log.info("Finished ModifyInfoUserUseCaseImpl.modifyUserById method"))
             .doOnError(e -> log.error("Finished ModifyInfoUserUseCaseImpl.modifyUserById method with error", e));
  }
}
