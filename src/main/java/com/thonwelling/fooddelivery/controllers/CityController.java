package com.thonwelling.fooddelivery.controllers;

import com.thonwelling.fooddelivery.exceptionHandler.ApiError;
import com.thonwelling.fooddelivery.exceptions.BusinessException;
import com.thonwelling.fooddelivery.exceptions.NotFoundEntityException;
import com.thonwelling.fooddelivery.exceptions.StateNotFoundException;
import com.thonwelling.fooddelivery.models.City;
import com.thonwelling.fooddelivery.repositories.CityRepository;
import com.thonwelling.fooddelivery.services.CityService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
  public ResponseEntity<List<City>> listAllCities () {
    return ResponseEntity.status(HttpStatus.OK).body(cityService.listAllCities());
  }

  @GetMapping(value = "/{cityId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public City getOneCityById (@PathVariable UUID cityId) {
    return  cityService.FindCityById(cityId);
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  public City addOneNewCity (@RequestBody City city) {
    try {
      return cityService.addNewCity(city);
    } catch (StateNotFoundException error) {
        throw new BusinessException(error.getMessage(), error);
    }
  }

  @PutMapping(value = "/{cityId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ResponseStatus(HttpStatus.OK)
  public City updateOneCityById (@PathVariable UUID cityId , @RequestBody City city) {
      try {
        City currentCity = cityService.FindCityById(cityId);
        BeanUtils.copyProperties(city, currentCity, "id");
         return cityService.addNewCity(currentCity);

      } catch (StateNotFoundException error) {
        throw new BusinessException(error.getMessage(), error);
      }
  }

  @DeleteMapping("/{cityId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteOneCityById (@PathVariable UUID cityId) {
    cityRepository.deleteById(cityId);
  }

  @ExceptionHandler(NotFoundEntityException.class)
  public ResponseEntity<?> handleNotFoundEntityException(NotFoundEntityException notFoundEntityException){
    ApiError apiError = ApiError.builder()
            .dateTime(LocalDateTime.now())
            .message(notFoundEntityException.getMessage())
            .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<?> handleBusinessException(BusinessException businessException){
    ApiError apiError = ApiError.builder()
            .dateTime(LocalDateTime.now())
            .message(businessException.getMessage())
            .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
  }

}
