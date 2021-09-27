package com.example.user.adapter.input.web.user.model;

import com.example.user.adapter.input.web.user.model.request.SaveUserRestRequest;

import java.util.Collections;

public class SaveUserRestRequestMock {

  public static SaveUserRestRequest getMock() {
    return SaveUserRestRequest.builder()
             .name("Jose Luis")
             .email("mail@mail.com")
             .password("Password22")
             .phones(Collections.singletonList(SaveUserPhonesRestRequestMock.getMock()))
             .build();
  }
}
