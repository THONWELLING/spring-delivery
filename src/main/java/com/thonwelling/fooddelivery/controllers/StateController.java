package com.thonwelling.fooddelivery.controllers;

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

  @GetMapping(value = "/{stateId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public State getOneStateById (@PathVariable UUID stateId) {
    return stateService.FindStateById(stateId);
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public State addNewState(@RequestBody State state ) {
    return stateService.addNewState(state);
  }

  @PutMapping(value = "/{stateId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ResponseStatus(HttpStatus.OK)
  public State updateOneStateById (@PathVariable UUID stateId, @RequestBody State state) {
    State currentState = stateService.FindStateById(stateId);
    BeanUtils.copyProperties(state, currentState, "id");
      return stateService.addNewState(currentState);
  }
  @DeleteMapping("/{stateId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteOneStateById (@PathVariable UUID stateId) {
        stateService.DeleteStateById(stateId);
  }



}
