package com.thonwelling.fooddelivery.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  @Column(nullable = false, length = 30)
  private String name;

  @Column(nullable = false, length = 100)
  private String email;

  @Column(nullable = false, length = 15)
  private String password;

  @JsonIgnore
  @Column(nullable = false)
  @CreationTimestamp
  private LocalDateTime registrationDate;

  @ManyToMany
  @JoinTable(name = "user_collection", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "collection_id"))
  private List<Collection> collections = new ArrayList<>();

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