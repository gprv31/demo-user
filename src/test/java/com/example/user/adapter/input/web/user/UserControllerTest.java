package com.example.user.adapter.input.web.user;

import com.example.user.adapter.input.web.user.model.request.SaveUserRestRequest;
import com.example.user.adapter.input.web.user.model.response.UserRestResponse;
import com.example.user.adapter.input.web.user.model.EditUserRestRequestMock;
import com.example.user.adapter.input.web.user.model.SaveUserRestRequestMock;
import com.example.user.domain.port.input.GetUserInfoUseCase;
import com.example.user.domain.port.input.ModifyInfoUserUseCase;
import com.example.user.domain.port.input.UserUseCase;
import com.example.user.entity.user.User;
import com.example.user.entity.user.request.UserMock;
import com.example.user.entity.user.request.UserRequest;
import com.example.user.entity.user.request.UserRequestMock;
import io.reactivex.Single;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.validation.DataBinder;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserControllerTest {

  private UserController userController;

  @Mock
  private UserUseCase userUseCase;

  @Mock
  private GetUserInfoUseCase getUserInfoUseCase;

  @Mock
  private ModifyInfoUserUseCase modifyInfoUserUseCase;

  @Mock
  private Function<SaveUserRestRequest, UserRequest> userRequestConverter;

  @Mock
  private Function<User, UserRestResponse> userRestResponseConverter;

  @Before
  public void before() {
    userController = new UserController(userUseCase, getUserInfoUseCase, modifyInfoUserUseCase,
        userRequestConverter, userRestResponseConverter);
  }


  @Test
  public void initBinderTest() {
    assertThat(userController).isNotNull();
    userController.initBinder(new DataBinder(null));
  }

  @Test
  public void getUserByIdTest() {
    String userId = "d11637-d201-4a9f-97fb-a5e362253400";
    when(userRestResponseConverter.apply(any())).thenReturn(UserRestResponse.builder().build());
    when(getUserInfoUseCase.getUserById(any())).thenReturn(Single.just(UserMock.getMock()));

    userController.getUserById(userId)
      .test()
      .assertComplete()
      .assertNoErrors()
      .dispose();
  }

  @Test
  public void saveUserTest() {

    String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJleHAiOj";
    when(userUseCase.saveUser(any())).thenReturn(Single.just(UserMock.getMock()));
    when(userRequestConverter.apply(any())).thenReturn(UserRequestMock.getMock());
    when(userRestResponseConverter.apply(any())).thenReturn(UserRestResponse.builder().build());

    userController.saveUser(token, SaveUserRestRequestMock.getMock())
      .test()
      .assertComplete()
      .assertNoErrors()
      .dispose();
  }

  @Test
  public void modifyHeroByIdTest() {
    String token = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJleHAiOj";
    String userId = "d11637-d201-4a9f-97fb-a5e362253400";
    when(userRequestConverter.apply(any())).thenReturn(UserRequestMock.getMock());
    when(modifyInfoUserUseCase.modifyUserById(any(), any())).thenReturn(Single.just(UserMock.getMock()));
    when(userRestResponseConverter.apply(any())).thenReturn(UserRestResponse.builder().build());

    userController.modifyUserById(token, userId, EditUserRestRequestMock.getMock())
      .test()
      .assertComplete()
      .assertNoErrors()
      .dispose();
  }


}
