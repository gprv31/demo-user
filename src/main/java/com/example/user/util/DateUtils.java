package com.example.user.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

  public static final String REST_DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";

  public static String format(LocalDateTime localDateTime, String pattern) {
    if (localDateTime == null) {
      return null;
    }
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    return localDateTime.format(formatter);
  }
}
