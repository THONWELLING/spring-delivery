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
  public ResponseEntity<State> getStateById(@PathVariable UUID id) {
    return stateService.getStateById(id);
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public State addState(@RequestBody State state ) {
    return stateService.addState(state);
  }

  @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ResponseStatus(HttpStatus.OK)
  public State updateState(@PathVariable UUID id, @RequestBody State state) {
      return stateService.addState(state);
  }
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteStateById(@PathVariable UUID id) {
        stateService.DeleteState(id);
  }

}
