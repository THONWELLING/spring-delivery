package com.thonwelling.fooddelivery.repositories;

import com.thonwelling.fooddelivery.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {
  List<Restaurant> findRestaurantByDeliveryRateBetween(BigDecimal lowerDeliveryRate, BigDecimal higherDeliveryRate);
  List<Restaurant> findRestaurantByNameContainingAndKitchenId(String name, UUID kitchenId);
  Optional<Restaurant> findFirstRestaurantByNameContaining(String name);

}
