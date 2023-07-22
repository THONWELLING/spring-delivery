package com.thonwelling.fooddelivery.repositories.exceptions;

import java.io.Serial;

public class NotFoundEntityException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1L;

  public NotFoundEntityException(String message) {
    super(message);
  }
}
