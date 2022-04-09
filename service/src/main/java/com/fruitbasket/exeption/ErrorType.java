package com.fruitbasket.exeption;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.HttpStatus;

public enum ErrorType {
  INTERNAL_ERROR(100, "An internal server error occurred", INTERNAL_SERVER_ERROR),
  FRUIT_DOES_NOT_EXIST(101, "Fruits does not exits", NOT_FOUND),
  FRUIT_DOES_MORE_THAN_QUANTITY(
      102, "Quantity requested is more than total available fruits ", NOT_ACCEPTABLE);

  private int id;
  private String description;
  private HttpStatus httpStatus;

  ErrorType(int id, String description, HttpStatus httpStatus) {

    this.id = id;
    this.description = description;
    this.httpStatus = httpStatus;
  }

  public int getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }
}
