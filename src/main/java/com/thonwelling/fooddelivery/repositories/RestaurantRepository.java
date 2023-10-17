package com.thonwelling.fooddelivery.repositories;

import com.thonwelling.fooddelivery.models.Restaurant;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RestaurantRepository extends CustomJpaRepository<Restaurant, UUID>, JpaSpecificationExecutor<Restaurant> {
  @Query("from Restaurant restaurant join restaurant.kitchen")
  List<Restaurant> findAll();

  List<Restaurant> findRestaurantByDeliveryRateBetween(BigDecimal lowerDeliveryRate, BigDecimal higherDeliveryRate);
//  @Query("from Restaurant where name like %:name% and kitchen.id = :kitchenId")
//  List<Restaurant> findRestaurantByNameAndId(String name, UUID id);

  List<Restaurant> findRestaurantByNameContainingAndKitchenId(String name, UUID id);
  Optional<Restaurant> findFirstRestaurantByNameContaining(String name);

}
