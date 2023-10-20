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
    if (!(o instanceof PurchaseOrder purchaseOrder)) return false;
    return Objects.equals(getOrderId(), purchaseOrder.getOrderId()) && Objects.equals(getSubtotal(), purchaseOrder.getSubtotal()) && Objects.equals(getTaxDelivery(), purchaseOrder.getTaxDelivery()) && Objects.equals(getTotalValue(), purchaseOrder.getTotalValue()) && Objects.equals(getDelliveryAddress(), purchaseOrder.getDelliveryAddress()) && getStatus() == purchaseOrder.getStatus() && Objects.equals(getDateCreation(), purchaseOrder.getDateCreation()) && Objects.equals(getDateConfirmation(), purchaseOrder.getDateConfirmation()) && Objects.equals(getCanceledAt(), purchaseOrder.getCanceledAt()) && Objects.equals(getDateDelivered(), purchaseOrder.getDateDelivered());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getOrderId(), getSubtotal(), getTaxDelivery(), getTotalValue(), getDelliveryAddress(), getStatus(), getDateCreation(), getDateConfirmation(), getCanceledAt(), getDateDelivered());
  }
}
