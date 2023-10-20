package com.thonwelling.fooddelivery.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundEntityException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1L;

  public NotFoundEntityException(String message) {
    super(message);
  }
}
