package com.example.user.domain.interactor;

import com.example.user.domain.port.input.UserUseCase;
import com.example.user.domain.port.output.UserPort;
import com.example.user.entity.user.request.UserMock;
import com.example.user.entity.user.request.UserRequestMock;
import io.reactivex.Single;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserUseCaseImplTest {

  private UserUseCase userUseCase;

  @Mock
  private UserPort userPort;

  @Before
  public void before() {
    userUseCase = new UserUseCaseImpl(userPort);
  }

  @Test
  public void saveUserTest() {
    when(userPort.saveUser(any())).thenReturn(Single.just(UserMock.getMock()));

    userUseCase.saveUser(UserRequestMock.getMock())
      .test()
      .assertComplete()
      .assertNoErrors()
      .dispose();
  }
}
