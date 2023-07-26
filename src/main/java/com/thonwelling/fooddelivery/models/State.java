package com.thonwelling.fooddelivery.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class State implements Serializable {
  @Serial
  private static final long serialVersionUID =1L;

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(nullable = false)
  private String name;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "city_id", nullable = false)
  private City city;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof State state)) return false;
    return Objects.equals(getId(), state.getId()) && Objects.equals(getName(), state.getName()) && Objects.equals(getCity(), state.getCity());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getCity());
  }
}