package com.example.user.application.configuration;

import com.example.user.adapter.input.web.user.model.converter.request.UserRequestConverter;
import com.example.user.adapter.input.web.user.model.converter.response.UserRestResponseConverter;
import com.example.user.adapter.input.web.user.model.request.SaveUserRestRequest;
import com.example.user.adapter.input.web.user.model.response.UserRestResponse;
import com.example.user.adapter.output.h2.UserAuthDetailsImpl;
import com.example.user.domain.interactor.GetAuthUserInformationUseCaseImpl;
import com.example.user.domain.interactor.GetUserInfoUseCaseImpl;
import com.example.user.domain.interactor.ModifyInfoUserUseCaseImpl;
import com.example.user.domain.interactor.UserUseCaseImpl;
import com.example.user.domain.port.input.GetAuthUserInformationUseCase;
import com.example.user.domain.port.input.GetUserInfoUseCase;
import com.example.user.domain.port.input.ModifyInfoUserUseCase;
import com.example.user.domain.port.input.UserUseCase;
import com.example.user.domain.port.output.UserPort;
import com.example.user.entity.user.User;
import com.example.user.entity.user.request.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Slf4j
@Configuration
public class ControllerConfiguration {

  /* UseCase Beans Injection */

  @Bean
  public UserUseCase userUseCase(UserPort userPort) {
    return new UserUseCaseImpl(userPort);
  }

  @Bean
  public GetAuthUserInformationUseCase getUserInformationUseCase(UserAuthDetailsImpl userAuthDetails) {
    return new GetAuthUserInformationUseCaseImpl(userAuthDetails);
  }

  @Bean
  public GetUserInfoUseCase getUserInfoUseCase(UserPort userPort) {
    return new GetUserInfoUseCaseImpl(userPort);
  }

  @Bean
  public ModifyInfoUserUseCase modifyInfoUserUseCase(UserPort userPort) {
    return new ModifyInfoUserUseCaseImpl(userPort);
  }

  /* Request Converter Beans Injection */

  @Bean
  public Function<SaveUserRestRequest, UserRequest> userRequestConverter() {
    return new UserRequestConverter();
  }

  /* Response Converter Beans Injection */

  @Bean
  public Function<User, UserRestResponse> saveUserRestResponseConverter() {
    return new UserRestResponseConverter();
  }
}
