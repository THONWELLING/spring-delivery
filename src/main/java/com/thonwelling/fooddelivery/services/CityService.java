package com.thonwelling.fooddelivery.services;

import com.thonwelling.fooddelivery.exceptions.InUseEntityException;
import com.thonwelling.fooddelivery.exceptions.NotFoundEntityException;
import com.thonwelling.fooddelivery.models.City;
import com.thonwelling.fooddelivery.models.State;
import com.thonwelling.fooddelivery.repositories.CityRepository;
import com.thonwelling.fooddelivery.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CityService {

  public static final String CITY_NOT_FOUND = "City With The Code %s Does Not Exists!!";
  public static final String CITY_IN_USE = "The Kitchen With Code %s Can Not Been Deteted. It Is In Use!!";
  @Autowired
  CityRepository cityRepository;

  @Autowired
  StateRepository stateRepository;

  public List<City> listCities() {
    return cityRepository.findAll();
  }

  public ResponseEntity<City> getCityById(@PathVariable UUID id) {
    return cityRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  public City addCity(City city) {
    UUID stateId = city.getState().getId();
    State state = stateRepository.findById(stateId)
        .orElseThrow(() -> new NotFoundEntityException(String.format(CITY_NOT_FOUND, stateId)));
    city.setState(state);
    return cityRepository.save(city);
  }

  @Transactional
  public void deleteCity(UUID id) {
    try {
      cityRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new NotFoundEntityException(String.format(CITY_NOT_FOUND, id));
    } catch (DataIntegrityViolationException e) {
      throw new InUseEntityException(String.format(CITY_IN_USE, id));
    }
  }
}
