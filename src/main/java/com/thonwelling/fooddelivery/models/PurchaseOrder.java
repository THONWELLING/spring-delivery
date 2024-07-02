package com.thonwelling.fooddelivery.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PurchaseOrder {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID   orderId;
  private Double subtotal;
  private Double taxDelivery;
  private Double totalValue;

  @Embedded
  private Address delliveryAddress;

  private OrderStatus status;

  @Column(nullable = false)
  @CreationTimestamp
  private LocalDateTime dateCreation;
  @CreationTimestamp
  private LocalDateTime dateConfirmation;
  @CreationTimestamp
  private LocalDateTime canceledAt;
  @CreationTimestamp
  private LocalDateTime dateDelivered;

  @ManyToOne
  @JoinColumn(nullable = false)
  private PaymentMode paymentMode;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Restaurant restaurant;

  @ManyToOne
  @JoinColumn(name = "user_client_id", nullable = false)
  private User user;

  @OneToMany(mappedBy = "purchaseOrder")
  private List<OrderItem> items = new ArrayList<>();

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PurchaseOrder that = (PurchaseOrder) o;
    return Objects.equals(orderId, that.orderId) && Objects.equals(subtotal, that.subtotal) && Objects.equals(taxDelivery, that.taxDelivery) && Objects.equals(totalValue, that.totalValue) && Objects.equals(delliveryAddress, that.delliveryAddress) && status == that.status && Objects.equals(dateCreation, that.dateCreation) && Objects.equals(dateConfirmation, that.dateConfirmation) && Objects.equals(canceledAt, that.canceledAt) && Objects.equals(dateDelivered, that.dateDelivered) && Objects.equals(paymentMode, that.paymentMode) && Objects.equals(restaurant, that.restaurant) && Objects.equals(user, that.user) && Objects.equals(items, that.items);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderId, subtotal, taxDelivery, totalValue, delliveryAddress, status, dateCreation, dateConfirmation, canceledAt, dateDelivered, paymentMode, restaurant, user, items);
  }
}
