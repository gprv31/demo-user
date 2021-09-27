package com.example.user.domain.interactor;

import com.example.user.adapter.output.h2.UserAuthDetailsImpl;
import com.example.user.domain.port.input.GetAuthUserInformationUseCase;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;

@Slf4j
@RequiredArgsConstructor
public class GetAuthUserInformationUseCaseImpl implements GetAuthUserInformationUseCase {

  private final UserAuthDetailsImpl userAuthDetails;

  @Override
  public Single<UserDetails> loadUserByUsername(String username) {
    log.info("Starting GetUserInformationUseCaseImpl.loadUserByUsername method");
    return Single.fromCallable(() -> userAuthDetails.loadUserByUsername(username))
        .doOnSuccess(i -> log.info("Finished GetUserInformationUseCaseImpl.loadUserByUsername method successfully"))
        .doOnError(e -> log.error("Finished GetUserInformationUseCaseImpl.loadUserByUsername method with error", e));
  }
}
