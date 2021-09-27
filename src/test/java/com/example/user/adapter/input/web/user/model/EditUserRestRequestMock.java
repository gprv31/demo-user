package com.example.user.adapter.input.web.user.model;

import com.example.user.adapter.input.web.user.model.request.EditUserRestRequest;

import java.util.Collections;

public class EditUserRestRequestMock {

  public static EditUserRestRequest getMock() {
    EditUserRestRequest restRequest = new EditUserRestRequest();
    restRequest.setName("Jose Luis");
    restRequest.setEmail("mail@mail.com");
    restRequest.setPhones(Collections.singletonList(SaveUserPhonesRestRequestMock.getMock()));
    return restRequest;
  }
}
