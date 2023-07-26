package com.thonwelling.fooddelivery.exceptions;


import java.io.Serial;

public class InUseEntityException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1L;

  public InUseEntityException(String message) {
    super(message);
  }
}
