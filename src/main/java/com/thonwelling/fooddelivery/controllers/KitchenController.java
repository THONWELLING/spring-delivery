package com.thonwelling.fooddelivery.controllers;

import com.thonwelling.fooddelivery.exceptions.InUseEntityException;
import com.thonwelling.fooddelivery.exceptions.NotFoundEntityException;
import com.thonwelling.fooddelivery.models.Kitchen;
import com.thonwelling.fooddelivery.repositories.KitchenRepository;
import com.thonwelling.fooddelivery.services.KitchenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {
  @Autowired
  KitchenRepository repository;
  @Autowired
  KitchenService service;

  @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<List<Kitchen>> KitchenList(){
    return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
  }

  @GetMapping( value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Kitchen> getKitchenById(@PathVariable UUID id){
    return service.getKitchenById(id);
  }

  @GetMapping(value = "/name", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public List<Kitchen>  findKitchenByName(String name) {
    return repository.findByNameContaining(name);
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  public Kitchen addKitchen(@RequestBody Kitchen kitchen) {
    return service.addKitchen(kitchen);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Kitchen> updateKitchen(@PathVariable UUID id,  @RequestBody Kitchen kitchen) {
    Kitchen kitchenFounded = service.getKitchenById(id).getBody();
    if (kitchenFounded != null) {
      BeanUtils.copyProperties(kitchen, kitchenFounded, "id");
        return ResponseEntity.ok(service.addKitchen(kitchenFounded));
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteKitchen (@PathVariable UUID id){
    service.deleteKitchen(id);
  }
}