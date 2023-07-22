package com.thonwelling.fooddelivery.exceptions;

import java.io.Serial;

public class NotFoundEntityException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1L;

  public NotFoundEntityException(String message) {
    super(message);
  }
}
