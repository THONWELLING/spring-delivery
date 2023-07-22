package com.thonwelling.fooddelivery.repositories;

import com.thonwelling.fooddelivery.models.Kitchen;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public interface KitchenRepository {
  List<Kitchen> listKitchen();
  Kitchen getKitchenById(UUID id);
  Kitchen saveKitchen(Kitchen kitchen);
  void deleteKitchen(UUID id);
}
