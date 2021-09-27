package com.example.user.adapter.input.web.user.model.request;

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
public class SaveUserPhonesRestRequest {

  @JsonProperty("number")
  @Schema(description = "Phone number", example = "98725859", required = true, type = "string")
  private String number;

  @JsonProperty("citycode")
  @Schema(description = "City Code", example = "1", required = true, type = "string")
  private String cityCode;

  @JsonProperty("countrycode")
  @Schema(description = "Country Code", example = "57", required = true, type = "string")
  private String countryCode;
}
