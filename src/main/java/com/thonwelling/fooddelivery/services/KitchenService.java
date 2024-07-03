package com.thonwelling.fooddelivery.services;

import com.thonwelling.fooddelivery.exceptions.InUseEntityException;
import com.thonwelling.fooddelivery.exceptions.KitchenNotFoundException;
import com.thonwelling.fooddelivery.models.Kitchen;
import com.thonwelling.fooddelivery.repositories.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;

@Service
public class KitchenService {
  public static final String KITCHEN_IN_USE = "The Kitchen With Code %s Can Not Been Deteted. It Is In Use!!";
  @Autowired
  KitchenRepository kitchenRepository;

  public ResponseEntity<Kitchen> getOneKitchenById(@PathVariable UUID id) {
    return kitchenRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  public List<Kitchen> getOneKitchenByName (String name) {
    return kitchenRepository.findByNameContaining(name);
  }
  public Kitchen addNewKitchen (Kitchen kitchen) {
    return kitchenRepository.save(kitchen);
  }

  @Transactional
  public void deleteOneKitchenById (UUID kitchenId) {
    try{
        kitchenRepository.deleteById(kitchenId);
    } catch (EmptyResultDataAccessException e ){
       throw new KitchenNotFoundException(kitchenId);
    }catch (DataIntegrityViolationException e) {
       throw new InUseEntityException(String.format(KITCHEN_IN_USE, kitchenId));
    }
  }

  public Kitchen findKitchenById (UUID kitchenId) {
    return kitchenRepository.findById(kitchenId)
        .orElseThrow(() -> new KitchenNotFoundException(kitchenId));
  }


}