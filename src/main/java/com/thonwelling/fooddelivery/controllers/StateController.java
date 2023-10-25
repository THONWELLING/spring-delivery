package com.thonwelling.fooddelivery.controllers;

import com.thonwelling.fooddelivery.models.State;
import com.thonwelling.fooddelivery.repositories.StateRepository;
import com.thonwelling.fooddelivery.services.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/states")
public class StateController {

  @Autowired
  StateService stateService;
  @Autowired
  private StateRepository stateRepository;

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<List<State>> listAllStates () {
    return ResponseEntity.status(HttpStatus.OK).body(stateService.listAllStates());
  }

  @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<State> getOneStateById (@PathVariable UUID id) {
    return stateService.getOneStateById(id);
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public State addNewState(@RequestBody State state ) {
    return stateService.addNewState(state);
  }

  @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ResponseStatus(HttpStatus.OK)
  public State updateOneStateById (@PathVariable UUID id, @RequestBody State state) {
      return stateService.addNewState(state);
  }
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteOneStateById (@PathVariable UUID id) {
        stateService.DeleteStateById(id);
  }



}
