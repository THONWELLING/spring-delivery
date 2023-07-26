package com.thonwelling.fooddelivery.services;

import com.thonwelling.fooddelivery.models.City;
import com.thonwelling.fooddelivery.models.State;
import com.thonwelling.fooddelivery.repositories.CityRepository;
import com.thonwelling.fooddelivery.repositories.StateRepository;
import com.thonwelling.fooddelivery.repositories.exceptions.NotFoundEntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StateService {

  @Autowired
  StateRepository stateRepository;
  @Autowired
  CityRepository cityRepository;
  public List<State> listStates() {
    return stateRepository.findAll();
  }

  public ResponseEntity<State> getStateById(@PathVariable UUID id) {
    return stateRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  public ResponseEntity<State> addState(State state) {
    UUID cityId = state.getCity().getId();
    Optional<City> city = cityRepository.findById(cityId);

    if (city.isEmpty()) {
      throw new NotFoundEntityException("The__City__With__Code__ " + cityId + "__ Does Not E xists!");
    }
    state.setCity(city.get());
    return ResponseEntity.status(HttpStatus.CREATED).body(stateRepository.save(state));
  }
}
