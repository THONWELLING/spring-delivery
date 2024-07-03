package com.thonwelling.fooddelivery.services;

import com.thonwelling.fooddelivery.exceptions.CityNotFoundException;
import com.thonwelling.fooddelivery.exceptions.InUseEntityException;
import com.thonwelling.fooddelivery.models.City;
import com.thonwelling.fooddelivery.models.State;
import com.thonwelling.fooddelivery.repositories.CityRepository;
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
public class CityService {
  public static final String CITY_IN_USE = "The Kitchen With Code %s Can Not Been Deleted. It Is In Use!!";
  @Autowired
  CityRepository cityRepository;
  @Autowired
  StateService stateService;

  public List<City> listAllCities() {
    return cityRepository.findAll();
  }

  public ResponseEntity<City> getOneCityById(@PathVariable UUID id) {
    return cityRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  public City addNewCity(City city) {
    UUID stateId = city.getState().getId();
    State state = stateService.FindStateById(stateId);
      city.setState(state);
      return cityRepository.save(city);
  }

  @Transactional
  public void deleteOneCityById (UUID cityId) {
    try {
      cityRepository.deleteById(cityId);
    } catch (EmptyResultDataAccessException e) {
      throw new CityNotFoundException(cityId);
    } catch (DataIntegrityViolationException e) {
      throw new InUseEntityException(String.format(CITY_IN_USE, cityId));
    }
  }

  public City FindCityById (UUID cityId) {
    return cityRepository.findById(cityId)
        .orElseThrow(() -> new CityNotFoundException(cityId));
  }


}