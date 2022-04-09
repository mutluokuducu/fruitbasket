package com.fruitbasket.componenttest.service;

import static com.fruitbasket.constant.FruitBasketConstants.ALL_FRUITS_URL;
import static com.fruitbasket.constant.FruitBasketConstants.CALC_FRUITS_URL;
import static com.fruitbasket.constant.FruitBasketConstants.FRUIT_BASKETS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import com.fruitbasket.componenttest.BaseComponentTest;
import com.fruitbasket.dto.response.FruitBasketResponse;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class FruitBasketServiceTest extends BaseComponentTest {

  @Test
  public void shouldGetFruitsBasketList() {
    FruitBasketResponse fruitBasketResponse =
        FruitBasketResponse.builder().fruitBasketList(FRUIT_BASKETS).build();
    ResponseEntity<FruitBasketResponse> response =
        restTemplate.exchange(
            serverUrl.apply(ALL_FRUITS_URL),
            HttpMethod.GET,
            buildHeader(),
            FruitBasketResponse.class);

    assertThat(response.getStatusCode()).isEqualTo(OK);
    assertThat(response.getBody()).isEqualTo(fruitBasketResponse);
  }

  @Test
  public void shouldReturnFruitsCalcByFruitNameAndQuantity_GetNotFoundRequest() {

    ResponseEntity<?> response =
        restTemplate.exchange(
            serverUrl.apply(CALC_FRUITS_URL),
            HttpMethod.GET,
            buildHeader(),
            Object.class,
            "apples",
            "10");

    assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
  }

  @Test
  public void shouldReturnFruitsCalcByFruitNameAndQuantity_MoreThanQuantity() {
    ResponseEntity<?> response =
        restTemplate.exchange(
            serverUrl.apply(CALC_FRUITS_URL),
            HttpMethod.GET,
            buildHeader(),
            Object.class,
            "apple",
            "100");
    assertThat(response.getStatusCode()).isEqualTo(NOT_ACCEPTABLE);
  }

  @Test
  public void shouldReturnFruitsCalcByFruitNameAndQuantity_ReturnTotalValue() {
    ResponseEntity<?> response =
        restTemplate.exchange(
            serverUrl.apply(CALC_FRUITS_URL),
            HttpMethod.GET,
            buildHeader(),
            Object.class,
            "apple",
            "10");
    assertThat(response.getBody()).isEqualTo(19.9);
  }

  private HttpEntity<?> buildHeader() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    return new HttpEntity<>(headers);
  }
}
