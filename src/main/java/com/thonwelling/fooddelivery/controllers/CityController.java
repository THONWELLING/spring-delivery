package com.thonwelling.fooddelivery.controllers;

import com.thonwelling.fooddelivery.models.City;
import com.thonwelling.fooddelivery.repositories.CityRepository;
import com.thonwelling.fooddelivery.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
  public ResponseEntity<City > getCityById(@PathVariable UUID id) {
    return  cityService.getCityById(id);
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  public City addCity(@RequestBody City city) {
      return cityService.addCity(city);

  }

  @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ResponseStatus(HttpStatus.OK)
  public City updateCity(@PathVariable UUID id , @RequestBody City city) {
    return cityService.addCity(city);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteCityById(@PathVariable UUID id) {
    cityRepository.deleteById(id);
  }
}
