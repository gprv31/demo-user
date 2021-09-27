package com.example.user.adapter.output.h2;

import com.example.user.adapter.output.h2.entity.UserEntity;
import com.example.user.adapter.output.h2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class UserAuthDetailsImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserEntity userEntity = userRepository.findByUsernameAndEnabled(username, Boolean.TRUE)
                  .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));
    return new User(userEntity.getUsername(), userEntity.getPassword(), getAuthority(userEntity.getRole()));
  }

  private List<SimpleGrantedAuthority> getAuthority(String role) {
    return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role));
  }

}
