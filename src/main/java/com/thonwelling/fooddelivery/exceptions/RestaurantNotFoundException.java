package com.thonwelling.fooddelivery.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RestaurantNotFoundException extends NotFoundEntityException {
  @Serial
  private static final long serialVersionUID = 1L;
  public RestaurantNotFoundException(String message) {
    super(message);
  }

  public RestaurantNotFoundException(UUID restaurantId) {
    this(String.format("Restaurant With Code %s Does Not Exists!!", restaurantId));
  }
}
