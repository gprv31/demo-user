package com.example.user.domain.port.input;

import io.reactivex.Single;
import org.springframework.security.core.userdetails.UserDetails;

public interface GetAuthUserInformationUseCase {

  Single<UserDetails> loadUserByUsername(String username);

}
