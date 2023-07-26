package com.thonwelling.fooddelivery.services;

import com.thonwelling.fooddelivery.exceptions.InUseEntityException;
import com.thonwelling.fooddelivery.exceptions.NotFoundEntityException;
import com.thonwelling.fooddelivery.models.State;
import com.thonwelling.fooddelivery.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Service
public class StateService {

  @Autowired
  StateRepository stateRepository;

  public List<State> listCities() {
    return stateRepository.findAll();
  }

  public ResponseEntity<State> getStateById(@PathVariable UUID id) {
    return stateRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  public State addState(State state) {
    return stateRepository.save(state);
  }

  public void DeleteState(@PathVariable UUID id) {
    try {
      stateRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e) {
        throw new NotFoundEntityException("State With code " + id + " Does Not Exists!");
    } catch (DataIntegrityViolationException e) {
        throw new InUseEntityException("State With code " + id + "Can Not Be Deleted!");
    }
  }
}
