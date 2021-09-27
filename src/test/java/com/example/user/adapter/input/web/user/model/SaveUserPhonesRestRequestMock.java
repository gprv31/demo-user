package com.example.user.adapter.input.web.user.model;

import com.example.user.adapter.input.web.user.model.request.SaveUserPhonesRestRequest;

public class SaveUserPhonesRestRequestMock {

  public static SaveUserPhonesRestRequest getMock() {
    return SaveUserPhonesRestRequest.builder()
             .number("98585214")
             .cityCode("1")
             .countryCode("54")
             .build();
  }
}
