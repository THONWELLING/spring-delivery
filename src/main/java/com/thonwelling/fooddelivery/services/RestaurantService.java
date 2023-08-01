package com.thonwelling.fooddelivery.services;

import com.thonwelling.fooddelivery.models.Kitchen;
import com.thonwelling.fooddelivery.models.Restaurant;
import com.thonwelling.fooddelivery.repositories.KitchenRepository;
import com.thonwelling.fooddelivery.repositories.RestaurantRepository;
import com.thonwelling.fooddelivery.exceptions.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RestaurantService {
  @Autowired
  RestaurantRepository restaurantRepository;
  @Autowired
  KitchenRepository kitchenRepository;

  public List<Restaurant> listRestaurants(){
    return restaurantRepository.findAll();
  }

  public ResponseEntity<Restaurant>  getRestaurantById(@PathVariable UUID id){
    return restaurantRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  public List<Restaurant>getRestaurantByDeliveryRate(BigDecimal lowerDeliveryRate, BigDecimal higherDeliveryRate){
    return restaurantRepository.findRestaurantByDeliveryRateBetween(lowerDeliveryRate, higherDeliveryRate);
  }

  public List<Restaurant>getRestaurantByNameAndKitchenId(String name, UUID kitchenId){
    return restaurantRepository.findRestaurantByNameContainingAndKitchenId(name, kitchenId);
  }
  public Optional<Restaurant>findFirstRestaurantByNameContaining(String name){
    return restaurantRepository.findFirstRestaurantByNameContaining(name);
  }
  public ResponseEntity<Restaurant> addRestaurant(Restaurant restaurant){
    UUID kitchenId = restaurant.getKitchen().getId();
    Optional<Kitchen> kitchen = kitchenRepository.findById(kitchenId);

    if (kitchen.isEmpty()){
      throw new NotFoundEntityException(
          String.format("The Kitchen With Code %s Does Not Exists!!", kitchenId)
      );
    }
    restaurant.setKitchen(kitchen.get());
    return ResponseEntity.status(HttpStatus.CREATED).body(restaurantRepository.save(restaurant));
  }
}
