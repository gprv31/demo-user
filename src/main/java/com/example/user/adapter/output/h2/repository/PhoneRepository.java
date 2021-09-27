package com.example.user.adapter.output.h2.repository;

import com.example.user.adapter.output.h2.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {

}
