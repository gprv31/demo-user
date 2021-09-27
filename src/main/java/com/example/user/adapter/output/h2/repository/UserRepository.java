package com.example.user.adapter.output.h2.repository;

import com.example.user.adapter.output.h2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

  Optional<UserEntity> findByUsernameAndEnabled(String username, Boolean enabled);
}
