package com.fruitbasket.controller;

import static com.fruitbasket.constant.FruitBasketConstants.ALL_FRUITS_URL;
import static com.fruitbasket.constant.FruitBasketConstants.CALC_FRUITS_URL;

import com.fruitbasket.dto.response.FruitBasketResponse;
import com.fruitbasket.services.FruitBasketService;
import io.swagger.annotations.ApiOperation;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FruitBasketController {

  @Autowired public FruitBasketService fruitBasketService;

  @ApiOperation(
      value = "Get all Fruit Baskets",
      nickname = "Get all Fruit Baskets",
      response = ResponseEntity.class)
  @GetMapping(value = ALL_FRUITS_URL)
  public ResponseEntity<FruitBasketResponse> getFruitBasket() {
    FruitBasketResponse response = fruitBasketService.getAllFruitBasket();
    return ResponseEntity.ok().body(response);
  }

  @ApiOperation(
      value = "Calculate FruitBaskets",
      nickname = "Calculate FruitBaskets",
      response = ResponseEntity.class)
  @GetMapping(value = CALC_FRUITS_URL)
  public ResponseEntity<BigDecimal> calcCostByFruitNameAndQuantity(
      @PathVariable(value = "fruit-name") String fruitName,
      @PathVariable(value = "quantity") int quantity) {

    return ResponseEntity.ok()
        .body(fruitBasketService.calcCostByFruitNameAndQuantity(fruitName, quantity));
  }
}
