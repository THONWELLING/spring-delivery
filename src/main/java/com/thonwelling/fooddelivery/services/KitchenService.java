package com.thonwelling.fooddelivery.services;

import com.thonwelling.fooddelivery.models.Kitchen;
import com.thonwelling.fooddelivery.repositories.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Service
public class KitchenService {

  @Autowired
  KitchenRepository kitchenRepository;


  public ResponseEntity<Kitchen> getKitchenById(@PathVariable UUID id) {
    return kitchenRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }
  public Kitchen addKitchen(Kitchen kitchen) {
    return kitchenRepository.save(kitchen);
  }

  @Transactional
  public void deleteKitchen( UUID id) {
    kitchenRepository.deleteById(id);
  }

}
