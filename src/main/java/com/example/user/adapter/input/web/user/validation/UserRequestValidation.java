package com.example.user.adapter.input.web.user.validation;

import com.example.user.adapter.input.web.user.model.request.EditUserRestRequest;
import com.example.user.adapter.input.web.user.model.request.SaveUserPhonesRestRequest;
import com.example.user.adapter.input.web.user.model.request.SaveUserRestRequest;
import com.example.user.application.exception.UserRestException;
import com.example.user.util.enums.ValidationErrors;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserRequestValidation {

  public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

  public static final Pattern VALID_PASSWORD_REGEX =
    Pattern.compile("(?=^.{4,}$)(?!.* )(?=.*\\d.*\\d)(?=.*[A-Z])(?=.*[a-z]).*$");

  public static final Pattern VALID_ONLY_LETTERS_AND_NUMBERS = Pattern.compile("^[A-Za-z0-9]*$");

  public static final Pattern VALID_ONLY_LETTERS = Pattern.compile("^[a-zA-Z ]*$");

  public static final Pattern VALID_ONLY_NUMBERS = Pattern.compile("^[0-9]*$");

  public static void validateSaveUserRestRequest(SaveUserRestRequest saveUserRestRequest) {
    if (saveUserRestRequest == null || allAttributesAreEmpty(saveUserRestRequest)) {
      throw new UserRestException(ValidationErrors.EMPTY_VALUES.getDetail(), null);
    }

    if (saveUserRestRequest.getName() == null || saveUserRestRequest.getName().isEmpty()
          || !matchWithPattern(VALID_ONLY_LETTERS, saveUserRestRequest.getName())) {
      throw new UserRestException(ValidationErrors.NAME.getDetail(), null);
    }

    if (saveUserRestRequest.getEmail() == null || saveUserRestRequest.getEmail().isEmpty()
          || !matchWithPattern(VALID_EMAIL_ADDRESS_REGEX, saveUserRestRequest.getEmail())) {
      throw new UserRestException(ValidationErrors.EMAIL.getDetail(), null);
    }

    if (saveUserRestRequest.getPassword() == null || saveUserRestRequest.getPassword().isEmpty()
          || !validPassword(saveUserRestRequest.getPassword())) {
      throw new UserRestException(ValidationErrors.PASSWORD.getDetail(), null);
    }

    checkValidPhones(saveUserRestRequest.getPhones());

  }



  public static void validateEditUserRestRequest(EditUserRestRequest editUserRestRequest) {
    if (editUserRestRequest == null || allAttributesAreEmpty(editUserRestRequest)) {
      throw new UserRestException(ValidationErrors.EMPTY_VALUES.getDetail(), null);
    }
    if (!StringUtils.isBlank(editUserRestRequest.getName())
          && !matchWithPattern(VALID_ONLY_LETTERS, editUserRestRequest.getName())) {
      throw new UserRestException(ValidationErrors.NAME.getDetail(), null);
    }

    if (!StringUtils.isBlank(editUserRestRequest.getEmail())
          && !matchWithPattern(VALID_EMAIL_ADDRESS_REGEX, editUserRestRequest.getEmail())) {
      throw new UserRestException(ValidationErrors.EMAIL.getDetail(), null);
    }

    if (!StringUtils.isBlank(editUserRestRequest.getPassword())
          && !validPassword(editUserRestRequest.getPassword())) {
      throw new UserRestException(ValidationErrors.PASSWORD.getDetail(), null);
    }

    checkValidPhones(editUserRestRequest.getPhones());
  }

  private static boolean allAttributesAreEmpty(SaveUserRestRequest saveUserRestRequest) {
    return StringUtils.isBlank(saveUserRestRequest.getEmail())
             && StringUtils.isBlank(saveUserRestRequest.getName())
             && StringUtils.isBlank(saveUserRestRequest.getPassword())
             && (saveUserRestRequest.getPhones() == null || saveUserRestRequest.getPhones().isEmpty());
  }

  private static void checkValidPhones(List<SaveUserPhonesRestRequest> phonesRestRequestList) {
    if (phonesRestRequestList != null) {
      for (SaveUserPhonesRestRequest p: phonesRestRequestList) {
        if (p.getNumber() == null || !matchWithPattern(VALID_ONLY_NUMBERS, p.getNumber())) {
          throw new UserRestException("Phone Number is invalid", null);
        }
        if (p.getCityCode() == null || !matchWithPattern(VALID_ONLY_NUMBERS, p.getCityCode())) {
          throw new UserRestException("City code is invalid", null);
        }
        if (p.getCountryCode() == null || !matchWithPattern(VALID_ONLY_NUMBERS, p.getCountryCode())) {
          throw new UserRestException("Country code is invalid", null);
        }
      }
    }
  }

  private static boolean validPassword(String passwordStr) {
    return matchWithPattern(VALID_PASSWORD_REGEX, passwordStr)
             && matchWithPattern(VALID_ONLY_LETTERS_AND_NUMBERS, passwordStr);
  }

  private static Boolean matchWithPattern(Pattern pattern, String field) {
    Matcher m = pattern.matcher(field.trim());
    return m.matches();
  }
}
