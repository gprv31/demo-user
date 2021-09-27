package com.example.user.domain.interactor;

import com.example.user.domain.port.input.GetUserInfoUseCase;
import com.example.user.domain.port.output.UserPort;
import com.example.user.entity.user.request.UserMock;
import io.reactivex.Single;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class GetUserInfoUseCaseImplTest {

  private GetUserInfoUseCase getUserInfoUseCase;

  @Mock
  private UserPort userPort;

  @Before
  public void before() {
    getUserInfoUseCase = new GetUserInfoUseCaseImpl(userPort);
  }


  @Test
  public void getUserByIdTest() {
    String userId = "d11637-d201-4a9f-97fb-a5e362253400";
    when(userPort.getUserById(any())).thenReturn(Single.just(UserMock.getMock()));

    getUserInfoUseCase.getUserById(userId)
      .test()
      .assertComplete()
      .assertNoErrors()
      .dispose();
  }
}
