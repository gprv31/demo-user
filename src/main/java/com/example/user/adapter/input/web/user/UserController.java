package com.example.user.adapter.input.web.user;

import com.example.user.adapter.input.web.user.model.request.EditUserRestRequest;
import com.example.user.adapter.input.web.user.model.request.SaveUserRestRequest;
import com.example.user.adapter.input.web.user.model.response.UserRestResponse;
import com.example.user.adapter.input.web.user.validation.UserRequestValidation;
import com.example.user.application.exception.UserExceptionResponse;
import com.example.user.domain.port.input.GetUserInfoUseCase;
import com.example.user.domain.port.input.ModifyInfoUserUseCase;
import com.example.user.domain.port.input.UserUseCase;
import com.example.user.entity.user.User;
import com.example.user.entity.user.request.UserRequest;
import io.reactivex.Single;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "User", description = "Users Controller")
public class UserController {

  private final UserUseCase userUseCase;
  private final GetUserInfoUseCase getUserInfoUseCase;
  private final ModifyInfoUserUseCase modifyInfoUserUseCase;

  private final Function<SaveUserRestRequest, UserRequest> userRequestConverter;

  private final Function<User, UserRestResponse> userRestResponseConverter;

  @InitBinder
  public void initBinder(DataBinder binder) {
    binder.setDisallowedFields();
  }

  /**
   * Save User information.
   *
   * @param saveUserRestRequest {@link SaveUserRestRequest}
   * @return {@link UserRestResponse}
   */
  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(value = "")
  @Operation(summary = "Save User information.", description = "Save user information.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Already OK.",
      content = {@Content(schema = @Schema(implementation = UserRestResponse.class))}),
    @ApiResponse(responseCode = "400", description = "Bad Request.",
      content = {@Content(schema = @Schema(implementation = UserExceptionResponse.class))}),
    @ApiResponse(responseCode = "500",
      description = "Internal Server Error.",
      content = {@Content(schema = @Schema(implementation = UserExceptionResponse.class))}),
    @ApiResponse(responseCode = "503",
      description = "Service Unavailable.",
      content = {@Content(schema = @Schema(implementation = Exception.class))})
  })
  public Single<UserRestResponse> saveUser(
      @RequestHeader("Authorization") String token,
      @RequestBody SaveUserRestRequest saveUserRestRequest) {
    log.info("Starting UserController.saveUser method");
    UserRequestValidation.validateSaveUserRestRequest(saveUserRestRequest);
    UserRequest userRequest = userRequestConverter.apply(saveUserRestRequest);
    userRequest.setToken(token.split("Bearer ")[1]);
    return userUseCase.saveUser(userRequest)
             .map(userRestResponseConverter::apply)
             .doOnSuccess(i -> log.info("Finished UserController.saveUser method"))
             .doOnError(e -> log.error("Finished UserController.saveUser method with error", e));
  }

  /**
   * Get user information by identifier.
   *
   * @param userUuid String
   * @return {@link UserRestResponse}
   */
  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping(value = "/{userId}")
  @Operation(summary = "Get User information by identifier.", description = "Get User by identifier.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Already OK.",
      content = {@Content(schema = @Schema(implementation = UserRestResponse.class))}),
    @ApiResponse(responseCode = "400", description = "Bad Request.",
      content = {@Content(schema = @Schema(implementation = UserExceptionResponse.class))}),
    @ApiResponse(responseCode = "500",
      description = "Internal Server Error.",
      content = {@Content(schema = @Schema(implementation = UserExceptionResponse.class))}),
    @ApiResponse(responseCode = "503",
      description = "Service Unavailable.",
      content = {@Content(schema = @Schema(implementation = Exception.class))})
  })
  public Single<UserRestResponse> getUserById(
    @PathVariable(value = "userId")
    @Schema(description = "User identifier", example = "1") String userUuid) {
    log.info("Starting UserController.getUserById method");
    return getUserInfoUseCase.getUserById(userUuid)
             .map(userRestResponseConverter::apply)
             .doOnSuccess(i -> log.info("Finished UserController.getUserById method"))
             .doOnError(e -> log.error("Finished UserController.getUserById method with error", e));
  }


  @PreAuthorize("hasRole('ADMIN')")
  @PatchMapping(value = "/{userId}")
  @Operation(summary = "Modify User information by identifier.", description = "Modify user by identifier.")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Already OK.",
      content = {@Content(schema = @Schema(implementation = UserRestResponse.class))}),
    @ApiResponse(responseCode = "400", description = "Bad Request.",
      content = {@Content(schema = @Schema(implementation = UserExceptionResponse.class))}),
    @ApiResponse(responseCode = "500",
      description = "Internal Server Error.",
      content = {@Content(schema = @Schema(implementation = UserExceptionResponse.class))}),
    @ApiResponse(responseCode = "503",
      description = "Service Unavailable.",
      content = {@Content(schema = @Schema(implementation = Exception.class))})
  })
  public Single<UserRestResponse> modifyUserById(
      @RequestHeader("Authorization") String token,
      @PathVariable("userId")
      @Schema(description = "User identifier", example = "1")  String userUuid,
      @RequestBody EditUserRestRequest editUserRestRequest) {
    log.info("Starting UserController.modifyUserById method");
    UserRequestValidation.validateEditUserRestRequest(editUserRestRequest);
    UserRequest userRequest = userRequestConverter.apply(editUserRestRequest);
    userRequest.setToken(token.split("Bearer ")[1]);
    return modifyInfoUserUseCase.modifyUserById(userUuid, userRequest)
             .map(userRestResponseConverter::apply)
             .doOnSuccess(i -> log.info("Finished UserController.modifyUserById method"))
             .doOnError(e -> log.error("Finished UserController.modifyUserById method with error", e));
  }
}
