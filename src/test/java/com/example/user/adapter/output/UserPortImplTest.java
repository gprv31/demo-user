package com.example.user.adapter.output;

import com.example.user.adapter.output.h2.UserPortImpl;
import com.example.user.adapter.output.h2.entity.PhoneEntity;
import com.example.user.adapter.output.h2.entity.UserEntity;
import com.example.user.adapter.output.h2.repository.PhoneRepository;
import com.example.user.adapter.output.h2.repository.UserRepository;
import com.example.user.domain.port.output.UserPort;
import com.example.user.entity.user.User;
import com.example.user.entity.user.request.UserMock;
import com.example.user.entity.user.request.UserRequestMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserPortImplTest {

  private UserPort userPort;

  @Mock
  private UserRepository userRepository;

  @Mock
  private PhoneRepository phoneRepository;

  @Mock
  private Function<UserEntity, User> userConverter;


  @Before
  public void before() {
    userPort = new UserPortImpl(userRepository, phoneRepository, userConverter);
  }

  @Test
  public void getUserByIdTest() {
    String userId = "d11637-d201-4a9f-97fb-a5e362253400";
    when(userRepository.findById(any())).thenReturn(Optional.of(new UserEntity()));
    when(userConverter.apply(any())).thenReturn(UserMock.getMock());

    userPort.getUserById(userId)
      .test()
      .assertComplete()
      .assertNoErrors()
      .dispose();
  }

  @Test
  public void saveUserTest() {
    when(userRepository.save(any())).thenReturn(new UserEntity());
    when(userConverter.apply(any())).thenReturn(UserMock.getMock());
    userPort.saveUser(UserRequestMock.getMock())
      .test()
      .assertComplete()
      .assertNoErrors()
      .dispose();
  }

  @Test
  public void modifyHeroByIdTest() {
    String userId = "d11637-d201-4a9f-97fb-a5e362253400";

    when(userRepository.findById(any())).thenReturn(Optional.of(new UserEntity()));
    when(userRepository.saveAndFlush(any())).thenReturn(new UserEntity());
    when(phoneRepository.saveAll(any())).thenReturn(Collections.singletonList(new PhoneEntity()));
    when(userConverter.apply(any())).thenReturn(UserMock.getMock());

    userPort.modifyUserById(userId, UserRequestMock.getMock())
      .test()
      .assertComplete()
      .assertNoErrors()
      .dispose();
  }
}
