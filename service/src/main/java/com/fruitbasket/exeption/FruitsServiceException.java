package com.fruitbasket.exeption;

public class FruitsServiceException extends RuntimeException {

  private final ErrorType errorType;

  public FruitsServiceException(ErrorType errorType) {
    this.errorType = errorType;
  }

  public ErrorType getErrorType() {
    return errorType;
  }
}
