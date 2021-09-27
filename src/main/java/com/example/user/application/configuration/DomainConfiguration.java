package com.example.user.application.configuration;

import com.example.user.adapter.output.h2.UserAuthDetailsImpl;
import com.example.user.adapter.output.h2.UserPortImpl;
import com.example.user.adapter.output.h2.converter.output.UserConverter;
import com.example.user.adapter.output.h2.entity.UserEntity;
import com.example.user.adapter.output.h2.repository.PhoneRepository;
import com.example.user.adapter.output.h2.repository.UserRepository;
import com.example.user.domain.port.output.UserPort;
import com.example.user.entity.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Slf4j
@Configuration
public class DomainConfiguration {

  /* Output Converter Beans Injection */
  @Bean
  public Function<UserEntity, User> userConverter() {
    return new UserConverter();
  }

  /* Port Beans Injection */

  @Bean
  public UserAuthDetailsImpl userDetailsService(UserRepository userRepository) {
    return new UserAuthDetailsImpl(userRepository);
  }

  @Bean
  public UserPort userPort(UserRepository userRepository, PhoneRepository phoneRepository,
                           Function<UserEntity, User> userConverter) {
    return new UserPortImpl(userRepository, phoneRepository, userConverter);
  }
}
