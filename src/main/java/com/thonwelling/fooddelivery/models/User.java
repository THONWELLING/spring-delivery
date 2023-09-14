package com.thonwelling.fooddelivery.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
  @Serial
  private static final long serialVersionUID =1L;

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @JsonIgnore
  @CreationTimestamp
  @Column(nullable = false, columnDefinition = "datetime")
  private LocalDateTime registrationDate;

  @ManyToMany
  @JoinTable(name = "user_group", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private List<Group> groups = new ArrayList<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User user)) return false;
    return Objects.equals(getId(), user.getId()) && Objects.equals(getName(), user.getName()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getRegistrationDate(), user.getRegistrationDate());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getEmail(), getRegistrationDate());
  }
}