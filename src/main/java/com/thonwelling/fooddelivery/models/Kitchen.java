package com.thonwelling.fooddelivery.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Kitchen implements Serializable {
  @Serial
  private static final long serialVersionUID =1L;

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(nullable = false)
  private String name;

  @JsonIgnore
  @OneToMany(mappedBy =  "kitchen")
  private List<Restaurant> restaurants = new ArrayList<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Kitchen kitchen)) return false;
    return Objects.equals(getId(), kitchen.getId()) && Objects.equals(getName(), kitchen.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName());
  }
}