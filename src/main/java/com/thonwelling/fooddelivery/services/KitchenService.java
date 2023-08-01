package com.thonwelling.fooddelivery.services;

import com.thonwelling.fooddelivery.exceptions.InUseEntityException;
import com.thonwelling.fooddelivery.exceptions.NotFoundEntityException;
import com.thonwelling.fooddelivery.models.Kitchen;
import com.thonwelling.fooddelivery.repositories.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Service
public class KitchenService {

  @Autowired
  KitchenRepository kitchenRepository;


  public ResponseEntity<Kitchen> getKitchenById(@PathVariable UUID id) {
    return kitchenRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  public List<Kitchen> getKitchenByName (String name) {
    return kitchenRepository.findByNameContaining(name);
  }
  public Kitchen addKitchen(Kitchen kitchen) {
    return kitchenRepository.save(kitchen);
  }

  @Transactional
  public void deleteKitchen(UUID id) {
    try{
        kitchenRepository.deleteById(id);
    } catch (EmptyResultDataAccessException e ){
       throw new NotFoundEntityException(String.format("The kitchen With te Code %s Does Not Exists!!", id));
    }catch (DataIntegrityViolationException e) {
       throw new InUseEntityException(String.format("The Kitchen With Code %s Can Not Been Deteted. It Is In Use!!", id));
    }
  }

}