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
    if (o == null || getClass() != o.getClass()) return false;
    OrderItem orderItem = (OrderItem) o;
    return Objects.equals(itemId, orderItem.itemId) && Objects.equals(unitPrice, orderItem.unitPrice) && Objects.equals(totalPrice, orderItem.totalPrice) && Objects.equals(quantity, orderItem.quantity) && Objects.equals(observation, orderItem.observation) && Objects.equals(purchaseOrder, orderItem.purchaseOrder) && Objects.equals(product, orderItem.product);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemId, unitPrice, totalPrice, quantity, observation, purchaseOrder, product);
  }
}
