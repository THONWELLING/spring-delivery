package com.thonwelling.fooddelivery.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CityNotFoundException extends NotFoundEntityException {
  @Serial
  private static final long serialVersionUID = 1L;
  public CityNotFoundException(String message) {
    super(message);
  }

  public CityNotFoundException(UUID cityId) {
    this(String.format("City With Code %s Does Not Exists!!", cityId));
  }
}
