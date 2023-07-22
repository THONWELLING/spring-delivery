package com.thonwelling.fooddelivery.services;

import com.thonwelling.fooddelivery.models.Restaurant;
import com.thonwelling.fooddelivery.repositories.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RestaurantService {
  @Autowired
  RestaurantRepository restaurantRepository;

  public List<Restaurant> listRestaurants(){
    return restaurantRepository.findAll();
  }

  public ResponseEntity<Restaurant> getRestaurantById(@PathVariable UUID id){
    return restaurantRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }
}
