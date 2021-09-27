package com.example.user.adapter.output.h2.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class AuditEntity {

  @Column(name = "enabled")
  private Boolean enabled;

  @Column(name = "creation_date")
  private LocalDateTime creationDate;

  @Column(name = "update_date")
  private LocalDateTime updateDate;

  @Column(name = "last_login")
  private LocalDateTime lastLogin;

  @PrePersist
  public void prePersist() {
    enabled = Boolean.TRUE;
  }
}
