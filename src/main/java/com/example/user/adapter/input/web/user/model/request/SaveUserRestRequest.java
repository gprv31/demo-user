package com.example.user.adapter.input.web.user.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserRestRequest {

  @Schema(description = "User name", example = "Juan Rodriguez", required = true, type = "string")
  private String name;

  @Schema(description = "User email", example = "mail@mail.com", required = true, type = "string")
  private String email;

  @Schema(description = "User password", example = "hunter2", required = true, type = "string")
  private String password;

  @Schema(description = "List of phone numbers")
  private List<SaveUserPhonesRestRequest> phones;
}
