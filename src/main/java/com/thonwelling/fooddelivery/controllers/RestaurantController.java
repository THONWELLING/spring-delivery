package com.thonwelling.fooddelivery.controllers;

import com.thonwelling.fooddelivery.models.Restaurant;
import com.thonwelling.fooddelivery.repositories.exceptions.NotFoundEntityException;
import com.thonwelling.fooddelivery.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

  @Autowired
  RestaurantService restaurantService;

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


}