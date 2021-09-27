package com.example.user.adapter.input.web.auth;

import com.example.user.adapter.input.web.auth.request.JwtRestRequest;
import com.example.user.adapter.input.web.auth.response.JwtRestResponse;
import com.example.user.application.security.JwtToken;
import com.example.user.domain.port.input.GetAuthUserInformationUseCase;
import com.example.user.util.AuthenticateUtil;
import io.reactivex.Single;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Authentication Controller")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final JwtToken jwtToken;
  private final GetAuthUserInformationUseCase getAuthUserInformationUseCase;

  /**
   *  Authenticate method.
   *
   * @param authenticationRequest {@link JwtRestRequest}
   * @return {@link JwtRestResponse}
   * @throws Exception
   *
   */
  @PostMapping(value = "/authenticate", produces = {MediaType.APPLICATION_JSON_VALUE})
  @Operation(summary = "Authenticate", description = "Authenticate user credentials", tags = { "authenticate" })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful operation",
          content = @Content(schema = @Schema(implementation = JwtRestResponse.class)))
  })
  public Single<ResponseEntity<JwtRestResponse>> createAuthenticationToken(
      @RequestBody JwtRestRequest authenticationRequest) throws Exception {
    AuthenticateUtil.authenticate(authenticationManager,
        authenticationRequest.getUsername(), authenticationRequest.getPassword());
    return getAuthUserInformationUseCase.loadUserByUsername(authenticationRequest.getUsername())
             .map(userDetails -> {
               String token = jwtToken.generateToken(userDetails);
               return ResponseEntity.ok(new JwtRestResponse(token));
             })
             .doOnSuccess(i -> log.info("Finished AuthController.createAuthenticationToken method successfully"))
             .doOnError(e -> log.error("Finished AuthController.createAuthenticationToken method with error", e));
  }

}
