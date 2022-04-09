package com.fruitbasket.exeption;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(com.fruitbasket.exeption.FruitsServiceException.class)
  @Order(HIGHEST_PRECEDENCE)
  @ResponseBody
  public ResponseEntity<com.fruitbasket.exeption.ErrorResponse> handleAdapterException(
      com.fruitbasket.exeption.FruitsServiceException exception) {
    com.fruitbasket.exeption.ErrorType errorMessage = exception.getErrorType();
    HttpStatus httpStatus = errorMessage.getHttpStatus();

    com.fruitbasket.exeption.ErrorResponse errorResponse =
        new com.fruitbasket.exeption.ErrorResponse();
    errorResponse.setId(errorMessage.getId());
    errorResponse.setDescription(errorMessage.getDescription());
    return new ResponseEntity<>(errorResponse, httpStatus);
  }

  @ExceptionHandler(Exception.class)
  @Order(HIGHEST_PRECEDENCE)
  @ResponseBody
  public ResponseEntity<com.fruitbasket.exeption.ErrorResponse> handleException(
      Exception exception) {
    com.fruitbasket.exeption.ErrorResponse errorResponse =
        new com.fruitbasket.exeption.ErrorResponse();
    errorResponse.setDescription(exception.getMessage());
    return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
  }
}
