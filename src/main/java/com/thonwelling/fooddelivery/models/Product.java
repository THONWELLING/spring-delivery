package com.thonwelling.fooddelivery.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  @Column(nullable = false, length = 30)
  private String name;
  @Column(nullable = false)
  private String description;
  @Column(nullable = false)
  private BigDecimal price;
  @Column(nullable = false)
  private Boolean active;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Restaurant restaurant;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(price, product.price) && Objects.equals(active, product.active) && Objects.equals(restaurant, product.restaurant);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, price, active, restaurant);
  }
}
