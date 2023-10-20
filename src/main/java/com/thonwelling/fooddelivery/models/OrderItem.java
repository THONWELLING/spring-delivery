package com.thonwelling.fooddelivery.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID itemId;
  private Double unitPrice;
  private Double totalPrice;
  private Integer quantity;
  private String observation;

  @ManyToOne
  @JoinColumn(name = "purchase_order_order_id", nullable = false)
  private PurchaseOrder purchaseOrder;

  @ManyToOne
  @JoinColumn(nullable = false)
  private Product product;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof OrderItem orderItem)) return false;
    return Objects.equals(getItemId(), orderItem.getItemId()) && Objects.equals(getUnitPrice(), orderItem.getUnitPrice()) && Objects.equals(getTotalPrice(), orderItem.getTotalPrice()) && Objects.equals(getQuantity(), orderItem.getQuantity()) && Objects.equals(getObservation(), orderItem.getObservation()) && Objects.equals(getPurchaseOrder(), orderItem.getPurchaseOrder()) && Objects.equals(getProduct(), orderItem.getProduct());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getItemId(), getUnitPrice(), getTotalPrice(), getQuantity(), getObservation(), getPurchaseOrder(), getProduct());
  }
}
