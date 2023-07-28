package com.thonwelling.fooddelivery.controllers;

import com.thonwelling.fooddelivery.models.Restaurant;
import com.thonwelling.fooddelivery.repositories.RestaurantRepository;
import com.thonwelling.fooddelivery.exceptions.NotFoundEntityException;
import com.thonwelling.fooddelivery.services.RestaurantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  private RestaurantRepository restaurantRepository;

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<List<Restaurant>> listRestaurants (){
    return ResponseEntity.status(HttpStatus.OK).body(restaurantService.listRestaurants());
  }

  @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Restaurant> getRestaurantById(@PathVariable UUID id){
    return restaurantService.getRestaurantById(id);
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
        BeanUtils.copyProperties(restaurant, restaurantFounded, "id");
        return ResponseEntity.ok(restaurantService.addRestaurant(restaurantFounded));
      }
      return ResponseEntity.notFound().build();
    } catch (NotFoundEntityException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PatchMapping("/{id}")
  public ResponseEntity<?> updateRestaurantParcial (@PathVariable UUID id , Map<String, Object> fields) {
    Optional<Restaurant> restaurantOptional = restaurantRepository.findById(id);
    if (restaurantOptional.isPresent()) {
      Restaurant restaurant = restaurantOptional.get();
      merge(fields, restaurant);
      restaurantRepository.save(restaurant);
      return ResponseEntity.ok().build();
    } else {
       return ResponseEntity.notFound().build();
    }

  }

  private static void merge(Map<String, Object> originFields, Restaurant restaurantDestiny) {
    originFields.forEach((propertyName, propertyValue) -> {
      System.out.println(propertyName + " = " + propertyValue);
    });
  }
}