package com.example.user.adapter.output.h2;

import com.example.user.adapter.output.h2.entity.PhoneEntity;
import com.example.user.adapter.output.h2.entity.UserEntity;
import com.example.user.adapter.output.h2.repository.PhoneRepository;
import com.example.user.adapter.output.h2.repository.UserRepository;
import com.example.user.domain.port.output.UserPort;
import com.example.user.entity.user.Phone;
import com.example.user.entity.user.User;
import com.example.user.entity.user.request.UserRequest;
import com.example.user.util.enums.RoleEnum;
import com.example.user.util.enums.ValidationErrors;
import io.reactivex.Single;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
public class UserPortImpl implements UserPort {

  private final UserRepository userRepository;
  private final PhoneRepository phoneRepository;

  private final Function<UserEntity, User> userConverter;

  @Override
  @Transactional(rollbackOn = Exception.class)
  public Single<User> saveUser(UserRequest userRequest) {
    log.info("Starting UserPortImpl.saveUser method");
    return Single.fromCallable(() -> {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userRequest.getName());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));
        userEntity.setToken(userRequest.getToken());
        userEntity.setEnabled(Boolean.TRUE);
        userEntity.setCreationDate(LocalDateTime.now());
        userEntity.setLastLogin(LocalDateTime.now());
        userRepository.save(userEntity);
        savePhoneList(userEntity, userRequest.getPhones());
        return userConverter.apply(userEntity);
    })
    .doOnSuccess(i -> log.info("Finished UserPortImpl.saveUser method"))
    .doOnError(e -> log.error("Finished UserPortImpl.saveUser method with error", e));
  }

  @Override
  public Single<User> getUserById(String uuid) {
    return Single.fromCallable(() -> {
      UserEntity userEntity = userRepository.findById(UUID.fromString(uuid))
                                .orElseThrow(() ->  new Exception("User not found"));
      return userConverter.apply(userEntity);
    });
  }

  @Override
  @Transactional(rollbackOn = Exception.class)
  public Single<User> modifyUserById(String uuid, UserRequest userRequest) {
    return Single.fromCallable(() -> {
      UserEntity userEntity = userRepository.findById(UUID.fromString(uuid))
                                .orElseThrow(() ->  new Exception(ValidationErrors.USER_NOT_FOUND.getDetail()));
      if (RoleEnum.ROLE_ADMIN.getValue().equals(userEntity.getRole())) {
        throw new Exception(ValidationErrors.USER_NOT_FOUND.getDetail());
      }

      if (!StringUtils.isBlank(userRequest.getName())) {
        userEntity.setUsername(userRequest.getName());
      }
      if (!StringUtils.isBlank(userRequest.getEmail())) {
        userEntity.setEmail(userRequest.getEmail());
      }
      if (!StringUtils.isBlank(userRequest.getPassword())) {
        userEntity.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));
      }
      userEntity.setToken(userRequest.getToken());
      userEntity.setLastLogin(LocalDateTime.now());
      userEntity.setUpdateDate(LocalDateTime.now());
      userRepository.saveAndFlush(userEntity);
      savePhoneList(userEntity, userRequest.getPhones());
      return userConverter.apply(userEntity);
    });
  }

  private void savePhoneList(UserEntity userEntity, List<Phone> phoneList) {
    List<PhoneEntity> phoneEntityList = new ArrayList<>();
    Optional.ofNullable(phoneList)
      .ifPresent(phones -> phones.forEach(phone -> {
        PhoneEntity phoneEntity = new PhoneEntity();
        phoneEntity.setPhoneNumber(phone.getNumber());
        phoneEntity.setCityCode(phone.getCityCode());
        phoneEntity.setCountryCode(phone.getCountryCode());
        phoneEntity.setUserEntity(userEntity);
        phoneEntityList.add(phoneEntity);
      }));
    phoneRepository.saveAll(phoneEntityList);
  }
}
