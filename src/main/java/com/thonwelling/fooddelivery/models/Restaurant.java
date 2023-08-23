package com.thonwelling.fooddelivery.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Restaurant implements Serializable {
  @Serial
  private static final long serialVersionUID =1L;

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private Double deliveryRate;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "kitchen_id", nullable = false)
  private Kitchen kitchen;

  @JsonIgnore
  @Embedded
  private Address address;

  @OneToMany
  @JsonIgnore
  @JoinTable(name = "restaurants_payment_mode", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "payment_mode_id"))
  private List<PaymentMode> paymentTypes = new ArrayList<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Restaurant that)) return false;
    return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getDeliveryRate(), that.getDeliveryRate());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getDeliveryRate());
  }
}