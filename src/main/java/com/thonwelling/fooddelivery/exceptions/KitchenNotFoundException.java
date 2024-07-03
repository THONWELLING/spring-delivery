package com.thonwelling.fooddelivery.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class KitchenNotFoundException extends NotFoundEntityException {
  @Serial
  private static final long serialVersionUID = 1L;
  public KitchenNotFoundException(String message) {
    super(message);
  }

  public KitchenNotFoundException(UUID kitchenId) {
    this(String.format("Kitchen With Code %s Does Not Exists!!", kitchenId));
  }
}
