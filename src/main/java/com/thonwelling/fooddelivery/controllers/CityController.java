package com.thonwelling.fooddelivery.controllers;

import com.thonwelling.fooddelivery.models.State;
import com.thonwelling.fooddelivery.exceptions.NotFoundEntityException;
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

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<List<State>> listStates(){
    return ResponseEntity.status(HttpStatus.OK).body(stateService.listStates());
  }

  @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<State> getStateById(@PathVariable UUID id) {
    return stateService.getStateById(id);
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<?> addState(@RequestBody State state) {
    try{
      state = stateService.addState(state).getBody();
      return ResponseEntity.status(HttpStatus.CREATED).body(state);
    } catch (NotFoundEntityException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }


}
