package com.thonwelling.fooddelivery.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thonwelling.fooddelivery.models.Restaurant;
import com.thonwelling.fooddelivery.repositories.RestaurantRepository;
import com.thonwelling.fooddelivery.exceptions.NotFoundEntityException;
import com.thonwelling.fooddelivery.services.RestaurantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

  @Autowired
  RestaurantService restaurantService;
  @Autowired
  RestaurantRepository restaurantRepository;

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<List<Restaurant>> listRestaurants (){
    return ResponseEntity.status(HttpStatus.OK).body(restaurantService.listRestaurants());
  }

  @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Restaurant> getRestaurantById(@PathVariable UUID id){
    return restaurantService.getRestaurantById(id);
  }

  @GetMapping(value = "/deliveryRate", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public List<Restaurant> getRestaurantByDeliveryRate(BigDecimal lowerDeliveryRate, BigDecimal higherDeliveryRate){
    return restaurantService.getRestaurantByDeliveryRate(lowerDeliveryRate, higherDeliveryRate);
  }

  @GetMapping(value = "/name-and-id/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public List<Restaurant> findRestaurantByNameContainingAndKitchenId(String name, @PathVariable UUID id){
    return restaurantService.findRestaurantByNameAndId(name, id);
  }
  @GetMapping(value = "/first-by-name", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public Optional<Restaurant> findFirstRestaurantByNameContaining(String name){
    return restaurantService.findFirstRestaurantByNameContaining(name);
  }

  @GetMapping("/first")
  public Optional<Restaurant> getFirstRestaurant() {
    return restaurantRepository.FindFirst();
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<?> addRestaurant(@RequestBody Restaurant restaurant) {
    try {
      restaurant = restaurantService.addRestaurant(restaurant).getBody();
      return ResponseEntity.status(HttpStatus.CREATED).body(restaurant);

    } catch (NotFoundEntityException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<?> updateRestaurant(@PathVariable UUID id , @RequestBody Optional<Restaurant> restaurant) {
    try {
      Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
      if (restaurantOptional.isPresent()) {
        Restaurant restaurantFounded = restaurantOptional.get();
        BeanUtils.copyProperties(restaurant, restaurantFounded, "id", "paymentMode");
        return restaurantService.addRestaurant(restaurantFounded);
      }
        return ResponseEntity.notFound().build();
    } catch (NotFoundEntityException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PatchMapping("/{id}")
  public ResponseEntity<?> updateRestaurantParcial(@PathVariable UUID id, @RequestBody Map<String, Object> fields) {
    Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
    if (restaurantOptional.isPresent()) {
      Restaurant restaurant = restaurantOptional.get();

      merge(fields, restaurant);

      try {
        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);
        return ResponseEntity.ok(updatedRestaurant);
      } catch (NotFoundEntityException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
      }
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  private void merge(Map<String, Object> originFields, Restaurant restaurantDestiny) {
    ObjectMapper objectMapper = new ObjectMapper();
    Restaurant restaurantOrigin = objectMapper.convertValue(originFields, Restaurant.class);

    // No loop abaixo, atribuímos os valores dos campos atualizados do restaurantOrigin para o restaurantDestiny
    originFields.forEach((propertyName, propertyValue) -> {
      Field field = ReflectionUtils.findField(Restaurant.class, propertyName);
      field.setAccessible(true);

      Object newValue = ReflectionUtils.getField(field, restaurantOrigin);
      ReflectionUtils.setField(field, restaurantDestiny, newValue);
    });
  }
}