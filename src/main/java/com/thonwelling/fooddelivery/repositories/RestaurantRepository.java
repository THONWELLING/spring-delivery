package com.thonwelling.fooddelivery.repositories;

import com.thonwelling.fooddelivery.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {}
