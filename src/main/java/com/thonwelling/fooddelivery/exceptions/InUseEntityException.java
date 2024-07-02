package com.thonwelling.fooddelivery.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class InUseEntityException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1L;

  public InUseEntityException(String message) {
    super(message);
  }
}
