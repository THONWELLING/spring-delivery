package com.thonwelling.fooddelivery.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thonwelling.fooddelivery.exceptions.BusinessException;
import com.thonwelling.fooddelivery.models.Kitchen;
import com.thonwelling.fooddelivery.models.Restaurant;
import com.thonwelling.fooddelivery.repositories.KitchenRepository;
import com.thonwelling.fooddelivery.repositories.RestaurantRepository;
import com.thonwelling.fooddelivery.exceptions.NotFoundEntityException;
import com.thonwelling.fooddelivery.services.RestaurantService;
import jakarta.persistence.EntityNotFoundException;
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
  public static final String RESTAURANT_NOT_FOUND = "The kitchen With The Code %s Does Not Exists!!";
  public static final String RESTAURANT_IN_USE = "The Kitchen With Code %s Can Not Been Deteted. It Is In Use!!";

  @Autowired
  RestaurantService restaurantService;
  @Autowired
  RestaurantRepository restaurantRepository;
  @Autowired
  private final KitchenRepository kitchenRepository;
  public RestaurantController(KitchenRepository kitchenRepository) {
    this.kitchenRepository = kitchenRepository;
  }

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<List<Restaurant>> listAllRestaurants () {
    return ResponseEntity.status(HttpStatus.OK).body(restaurantService.listAllRestaurants());
  }

  @GetMapping(value = "/{restaurantId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public Restaurant getRestaurantById (@PathVariable UUID restaurantId) {
    return restaurantService.findRestaurantById(restaurantId);
  }

  @GetMapping(value = "/deliveryRate", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public List<Restaurant> getRestaurantByDeliveryRate (BigDecimal lowerDeliveryRate, BigDecimal higherDeliveryRate) {
    return restaurantService.getRestaurantByDeliveryRate(lowerDeliveryRate, higherDeliveryRate);
  }

  @GetMapping(value = "/name-and-id/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public List<Restaurant> findRestaurantByNameContainingAndKitchenId (String name, @PathVariable UUID id) {
    return restaurantService.findRestaurantByNameAndId(name, id);
  }
  @GetMapping(value = "/first-by-name", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public Optional<Restaurant> findFirstRestaurantByNameContaining (String name) {
    return restaurantService.findFirstRestaurantByNameContaining(name);
  }

  @GetMapping("/first")
  public Optional<Restaurant> getFirstRestaurant () {
    return restaurantRepository.FindFirst();
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  public Restaurant addNewRestaurant (@RequestBody Restaurant restaurant) {
    try {
      return restaurantService.addNewRestaurant(restaurant);
    } catch (EntityNotFoundException error) {
      throw new BusinessException(error.getMessage());
    }
  }

  @PutMapping(value = "/{restaurantId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public Restaurant updateOneRestaurantById (@PathVariable UUID restaurantId , @RequestBody Restaurant restaurant) {
      Restaurant restaurantFounded = restaurantService.findRestaurantById(restaurantId);
          BeanUtils.copyProperties(restaurant, restaurantFounded, "id", "paymentMode", "address", "registrationDate", "products");
      try {
        return restaurantService.addNewRestaurant(restaurantFounded);
      } catch (EntityNotFoundException error) {
        throw new BusinessException(error.getMessage());
      }
  }

  @PatchMapping("/{restaurantId}")
  public Restaurant updateRestaurantParcial(@PathVariable UUID restaurantId, @RequestBody Map<String, Object> fields) {
    Restaurant currentRestaurant = restaurantService.findRestaurantById(restaurantId);
      merge(fields, currentRestaurant);
      return updateOneRestaurantById(restaurantId, currentRestaurant);
  }

  private void merge(Map<String, Object> originFields, Restaurant restaurantDestiny) {
    ObjectMapper objectMapper = new ObjectMapper();
    Restaurant restaurantOrigin = objectMapper.convertValue(originFields, Restaurant.class);

    // No loop abaixo, atribuímos os valores dos campos atualizados do restaurantOrigin para o restaurantDestiny
    originFields.forEach((propertyName, propertyValue) -> {
      Field field = ReflectionUtils.findField(Restaurant.class, propertyName);
      assert field != null;
      field.setAccessible(true);

      Object newValue = ReflectionUtils.getField(field, restaurantOrigin);
      ReflectionUtils.setField(field, restaurantDestiny, newValue);
    });
  }




}