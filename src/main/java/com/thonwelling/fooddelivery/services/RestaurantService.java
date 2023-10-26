package com.thonwelling.fooddelivery.services;

import com.thonwelling.fooddelivery.exceptions.NotFoundEntityException;
import com.thonwelling.fooddelivery.models.Kitchen;
import com.thonwelling.fooddelivery.models.Restaurant;
import com.thonwelling.fooddelivery.repositories.KitchenRepository;
import com.thonwelling.fooddelivery.repositories.RestaurantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RestaurantService {
  public static final String RESTAURANT_NOT_FOUND = "The Kitchen With Code %s Does Not Exists!!";
  @Autowired
  RestaurantRepository restaurantRepository;
  @Autowired
  KitchenRepository kitchenRepository;
  @Autowired
  KitchenService kitchenService;

  public List<Restaurant> listAllRestaurants (){
    return restaurantRepository.findAll();
  }

  public ResponseEntity<Restaurant> getOneRestaurantById (@PathVariable UUID id) {
    return restaurantRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  public List<Restaurant> getRestaurantByDeliveryRate (BigDecimal lowerDeliveryRate, BigDecimal higherDeliveryRate) {
    return restaurantRepository.findRestaurantByDeliveryRateBetween(lowerDeliveryRate, higherDeliveryRate);
  }

  public List<Restaurant> findRestaurantByNameAndId (String name, UUID id) {
    return restaurantRepository.findRestaurantByNameContainingAndKitchenId(name, id);
  }
  public Optional<Restaurant> findFirstRestaurantByNameContaining (String name) {
    return restaurantRepository.findFirstRestaurantByNameContaining(name);
  }
  public Restaurant addNewRestaurant (Restaurant restaurant) {
    UUID kitchenId = restaurant.getKitchen().getId();
    Kitchen kitchen = kitchenService.findKitchenById(kitchenId);
    restaurant.setKitchen(kitchen);
    return restaurantRepository.save(restaurant);
  }

  public Restaurant findRestaurantById (UUID restaurantId) {
    return restaurantRepository.findById(restaurantId)
        .orElseThrow(() -> new EntityNotFoundException(
            String.format(RESTAURANT_NOT_FOUND, restaurantId)));
  }



}
