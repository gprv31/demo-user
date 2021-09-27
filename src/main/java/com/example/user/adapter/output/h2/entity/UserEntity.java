package com.example.user.adapter.output.h2.entity;

import com.example.user.util.enums.RoleEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "Usuario")
public class UserEntity extends AuditEntity {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @ColumnDefault("random_uuid()")
  private UUID id;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userEntity")
  private List<PhoneEntity> phoneList = new ArrayList<>();

  @Column(name = "user_name")
  private String username;

  @Column(name = "encrypted_password")
  private String password;

  @Column(name = "token")
  private String token;

  @Column(name = "email", unique = true)
  private String email;

  @Column(name = "user_role")
  private String role;

  @PrePersist
  public void prePersist() {
    role = RoleEnum.ROLE_USER.getValue();
  }
}
