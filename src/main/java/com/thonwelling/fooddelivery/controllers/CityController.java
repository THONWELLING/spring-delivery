package com.thonwelling.fooddelivery.controllers;

import com.thonwelling.fooddelivery.exceptions.InUseEntityException;
import com.thonwelling.fooddelivery.exceptions.NotFoundEntityException;
import com.thonwelling.fooddelivery.models.City;
import com.thonwelling.fooddelivery.models.Restaurant;
import com.thonwelling.fooddelivery.repositories.CityRepository;
import com.thonwelling.fooddelivery.services.CityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/cities")
public class CityController {

  @Autowired
  CityService cityService;

  @Autowired
  CityRepository cityRepository;

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<List<City>>  listCities(){
    return ResponseEntity.status(HttpStatus.OK).body(cityService.listCities());
  }

  @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<City > getStateById(@PathVariable UUID id) {
    return  cityService.getCityById(id);
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<?> addState(@RequestBody City city) {
    try{
      city = cityService.addCity(city).getBody();
      return ResponseEntity.status(HttpStatus.CREATED).body(city);
    } catch (NotFoundEntityException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<?> updateCity(@PathVariable UUID id , @RequestBody Restaurant restaurant) {
    try {
      Optional<City> cityOptional = cityRepository.findById(id);
      if (cityOptional.isPresent()) {
        City cityFounded = cityOptional.get();
        BeanUtils.copyProperties(restaurant, cityFounded, "id");
        return ResponseEntity.ok(cityService.addCity(cityFounded));
      }
      return ResponseEntity.notFound().build();
    } catch (NotFoundEntityException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteCityById(@PathVariable UUID id) {
    try{
      cityRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    } catch (NotFoundEntityException e) {
       return ResponseEntity.notFound().build();
    } catch (InUseEntityException e) {
       return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
  }
}
