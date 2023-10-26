package com.thonwelling.fooddelivery.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;
import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StateNotFoundException extends NotFoundEntityException {
  @Serial
  private static final long serialVersionUID = 1L;
  public StateNotFoundException(String message) {
    super(message);
  }

  public StateNotFoundException(UUID stateId) {
    this(String.format("State With code %s Does Not Exists!!", stateId));

  }
}
