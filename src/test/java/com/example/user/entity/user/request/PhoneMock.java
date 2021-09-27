package com.example.user.entity.user.request;

import com.example.user.entity.user.Phone;

public class PhoneMock {

  public static Phone getMock() {
    return Phone.builder()
             .number("98585214")
             .cityCode("1")
             .countryCode("54")
             .build();
  }
}
