package com.example.user.adapter.input.web.user.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRestResponse {

  @JsonProperty("id")
  @Schema(description = "User identifier", example = "cc69c408-9f89-4cc5-9ea3-06ca2618bccf",
      required = true, type = "string")
  private String id;

  @JsonProperty("created")
  @Schema(description = "Created Date", example = "2021-05-04 14:10:15", required = true, type = "string")
  private String created;

  @JsonProperty("modified")
  @Schema(description = "Modified Date", example = "2021-05-04 09:22:15", required = true, type = "string")
  private String modified;

  @JsonProperty("last_login")
  @Schema(description = "Last Login Date", example = "2021-05-04 14:10:15", required = true, type = "string")
  private String lastLogin;

  @JsonProperty("token")
  @Schema(description = "Token", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbeyJhdXRob3JpdH",
      required = true, type = "string")
  private String token;

  @JsonProperty("isactive")
  @Schema(description = "User status", example = "true", required = true, type = "string")
  private Boolean active;
}
