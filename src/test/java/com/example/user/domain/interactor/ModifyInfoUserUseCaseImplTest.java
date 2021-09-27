package com.example.user.domain.interactor;

import com.example.user.domain.port.input.ModifyInfoUserUseCase;
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
public class ModifyInfoUserUseCaseImplTest {

  private ModifyInfoUserUseCase modifyInfoUserUseCase;

  @Mock
  private UserPort userPort;

  @Before
  public void before() {
    modifyInfoUserUseCase = new ModifyInfoUserUseCaseImpl(userPort);
  }

  @Test
  public void modifyHeroByIdTest() {
    String userId = "d11637-d201-4a9f-97fb-a5e362253400";

    when(userPort.modifyUserById(any(), any())).thenReturn(Single.just(UserMock.getMock()));

    modifyInfoUserUseCase.modifyUserById(userId, UserRequestMock.getMock())
      .test()
      .assertComplete()
      .assertNoErrors()
      .dispose();
  }
}
