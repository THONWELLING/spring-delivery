package com.thonwelling.fooddelivery.controllers;

import com.thonwelling.fooddelivery.exceptions.InUseEntityException;
import com.thonwelling.fooddelivery.exceptions.NotFoundEntityException;
import com.thonwelling.fooddelivery.models.State;
import com.thonwelling.fooddelivery.repositories.StateRepository;
import com.thonwelling.fooddelivery.services.StateService;
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
@RequestMapping("/states")
public class StateController {

  @Autowired
  StateService stateService;
  @Autowired
  private StateRepository stateRepository;

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<List<State>> listStates (){
    return ResponseEntity.status(HttpStatus.OK).body(stateService.listStates());
  }

  @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<State> getCityById(@PathVariable UUID id) {
    return stateService.getStateById(id);
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<?> addCity(@RequestBody State state ) {
    try{
      state = stateService.addState(state);
        return ResponseEntity.status(HttpStatus.CREATED).body(state);
    } catch (NotFoundEntityException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<State> updateState(@PathVariable UUID id, @RequestBody State state) {
    Optional<State> stateOptional = stateRepository.findById(id);
    if (stateOptional.isPresent()) {
      State stateFounded = stateOptional.get();
      BeanUtils.copyProperties(state, stateOptional, "id");
      return ResponseEntity.ok(stateService.addState(stateFounded));
    }
    return ResponseEntity.notFound().build();
  }
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteStateById(@PathVariable UUID id) {
    try{
        stateService.DeleteState(id);
        return ResponseEntity.noContent().build();
    } catch (NotFoundEntityException e) {
        return ResponseEntity.notFound().build();
    } catch (InUseEntityException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }
  }

}
