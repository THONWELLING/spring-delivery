package com.thonwelling.fooddelivery.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "restaurant")
public class Restaurant implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  @Column(nullable = false, length = 30)
  private String name;

  @Column(nullable = false)
  private Double deliveryRate;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "kitchen_id", nullable = false)
  private Kitchen kitchen;

  @JsonIgnore
  @Embedded
  private Address address;

  @JsonIgnore
  @Column(nullable = false)
  @CreationTimestamp
  private LocalDateTime registrationDate;

  @JsonIgnore
  @CreationTimestamp
  private LocalDateTime updateDate;


  @ManyToMany
  @JsonIgnore
  @JoinTable(name = "restaurant_payment_mode", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "payment_mode_id"))
  private List<PaymentMode> paymentTypes = new ArrayList<>();
/**
 * Adicionando Produto na entidade Restaurante
 *
 * Vamos agora referenciar na nossa entidade Restaurante, a entidade Produto que criamos.
 * Como 1 Restaurante pode ter vários produtos, o relacionamento aqui é Um para Muitos, ou, ManyToOne.
 * Adicionamos também a anotação @JsonIgnore, para evitar refêrencia circular, já que esse relacionamento é bi-direcional
 * @Autor Thonwelling
 * */
  @JsonIgnore
  @OneToMany(mappedBy = "restaurant")
  private List<Product>products = new ArrayList<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Restaurant that = (Restaurant) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(deliveryRate, that.deliveryRate) && Objects.equals(kitchen, that.kitchen) && Objects.equals(address, that.address) && Objects.equals(registrationDate, that.registrationDate) && Objects.equals(updateDate, that.updateDate) && Objects.equals(paymentTypes, that.paymentTypes) && Objects.equals(products, that.products);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, deliveryRate, kitchen, address, registrationDate, updateDate, paymentTypes, products);
  }
}