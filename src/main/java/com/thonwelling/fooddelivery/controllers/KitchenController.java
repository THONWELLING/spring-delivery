package com.thonwelling.fooddelivery.controllers;

import com.thonwelling.fooddelivery.models.Kitchen;
import com.thonwelling.fooddelivery.repositories.KitchenRepository;
import com.thonwelling.fooddelivery.services.KitchenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
  public ResponseEntity<List<Kitchen>> KitchenList () {
    return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
  }

  @GetMapping( value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<Kitchen> getOneKitchenById (@PathVariable UUID id){
    return service.getOneKitchenById(id);
  }

  @GetMapping(value = "/name", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public List<Kitchen> findOneKitchenByName (String name) {
    return repository.findByNameContaining(name);
  }

  @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  @ResponseStatus(HttpStatus.CREATED)
  public Kitchen addNewKitchen (@RequestBody Kitchen kitchen) {
    return service.addNewKitchen(kitchen);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Kitchen> updateOneKitchenById (@PathVariable UUID id,  @RequestBody Kitchen kitchen) {
    Kitchen kitchenFounded = service.getOneKitchenById(id).getBody();
    if (kitchenFounded != null) {
      BeanUtils.copyProperties(kitchen, kitchenFounded, "id");
        return ResponseEntity.ok(service.addNewKitchen(kitchenFounded));
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteOneKitchenById (@PathVariable UUID id){
    service.deleteOneKitchenById(id);
  }
}