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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CityService {

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

  public ResponseEntity<City> addCity(City city) {
    UUID stateId = city.getState().getId();
    Optional<State> state = stateRepository.findById(stateId);

    if (state.isEmpty()) {
      throw new NotFoundEntityException("The__City__With__Code__ " + stateId + "__ Does Not E xists!");
    }
    city.setState(state.get());
    return ResponseEntity.status(HttpStatus.CREATED).body(cityRepository.save(city));
  }

  @Transactional
  public void deleteCity(UUID id) {
    try {
      cityRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
      throw new NotFoundEntityException("City With code " + id + " Does Not Exists!");
    } catch (DataIntegrityViolationException e) {
      throw new InUseEntityException("City With code " + id + "Can Not Be Deleted!");
    }
  }
}
