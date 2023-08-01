package com.thonwelling.fooddelivery.repositories;

import com.thonwelling.fooddelivery.models.Kitchen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, UUID> {
  List<Kitchen> findByName(String name);
}
