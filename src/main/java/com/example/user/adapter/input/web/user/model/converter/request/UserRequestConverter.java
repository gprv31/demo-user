package com.example.user.adapter.input.web.user.model.converter.request;

import com.example.user.adapter.input.web.user.model.request.SaveUserPhonesRestRequest;
import com.example.user.adapter.input.web.user.model.request.SaveUserRestRequest;
import com.example.user.entity.user.Phone;
import com.example.user.entity.user.request.UserRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class UserRequestConverter implements Function<SaveUserRestRequest, UserRequest> {

  @Override
  public UserRequest apply(SaveUserRestRequest saveUserRestRequest) {
    if (saveUserRestRequest == null) {
      return null;
    }
    return UserRequest.builder()
             .email(saveUserRestRequest.getEmail())
             .name(saveUserRestRequest.getName())
             .password(saveUserRestRequest.getPassword())
             .phones(this.buildPhoneList(saveUserRestRequest.getPhones()))
             .build();
  }

  public List<Phone> buildPhoneList(List<SaveUserPhonesRestRequest> saveUserPhonesRestRequestList) {
    List<Phone> phoneList = new ArrayList<>();
    if (saveUserPhonesRestRequestList == null || saveUserPhonesRestRequestList.isEmpty()) {
      return Collections.emptyList();
    }
    saveUserPhonesRestRequestList.forEach(phonesRestRequest -> phoneList.add(Phone.builder()
                    .number(phonesRestRequest.getNumber())
                    .cityCode(phonesRestRequest.getCityCode())
                    .countryCode(phonesRestRequest.getCountryCode())
                    .build()));
    return phoneList;
  }
}
