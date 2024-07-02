package com.thonwelling.fooddelivery.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Table(name="kitchen")
public class Kitchen implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  @Column(nullable = false, length = 20)
  private String name;

  @JsonIgnore
  @OneToMany(mappedBy =  "kitchen")
  private List<Restaurant> restaurants = new ArrayList<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Kitchen kitchen = (Kitchen) o;
    return Objects.equals(id, kitchen.id) && Objects.equals(name, kitchen.name) && Objects.equals(restaurants, kitchen.restaurants);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, restaurants);
  }
}