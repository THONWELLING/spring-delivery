package com.thonwelling.fooddelivery.services;

import com.thonwelling.fooddelivery.exceptions.InUseEntityException;
import com.thonwelling.fooddelivery.exceptions.NotFoundEntityException;
import com.thonwelling.fooddelivery.exceptions.StateNotFoundException;
import com.thonwelling.fooddelivery.models.State;
import com.thonwelling.fooddelivery.repositories.StateRepository;
import jakarta.persistence.EntityNotFoundException;
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

  public static final String STATE_IN_USE = "State With code %s Can Not Been Deteted. It Is In Use!!";
  @Autowired
  StateRepository stateRepository;

  public List<State> listAllStates() {
    return stateRepository.findAll();
  }

  public ResponseEntity<State> getOneStateById (@PathVariable UUID stateId) {
    return stateRepository.findById(stateId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  public State addNewState(State state) {
    return stateRepository.save(state);
  }

  public void DeleteStateById (@PathVariable UUID stateId) {
    try {
      stateRepository.deleteById(stateId);
    } catch (EmptyResultDataAccessException e) {
        throw new StateNotFoundException(stateId);
    } catch (DataIntegrityViolationException e) {
        throw new InUseEntityException(String.format(STATE_IN_USE, stateId));
    }
  }

  public State FindStateById (UUID stateId) {
    return stateRepository.findById(stateId)
        .orElseThrow(() -> new StateNotFoundException (stateId));
  }


}
