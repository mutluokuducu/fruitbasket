package com.fruitbasket.constant;

import com.fruitbasket.dto.FruitBasket;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class FruitBasketConstants {
  public static final String ROOT_URL = "/api/v1/";
  public static final String ALL_FRUITS_URL = ROOT_URL + "allFruitBaskets";
  public static final String CALC_FRUITS_URL =
      ROOT_URL + "calcCostByFruitNameAndQuantity/{fruit-name}/{quantity}";

  public static final List<FruitBasket> FRUIT_BASKETS =
      Arrays.asList(
          new FruitBasket("apple", 10, new BigDecimal("1.99")),
          new FruitBasket("banana", 20, new BigDecimal("19.99")),
          new FruitBasket("orange", 10, new BigDecimal("29.99")),
          new FruitBasket("watermelon", 10, new BigDecimal("29.99")),
          new FruitBasket("papaya", 20, new BigDecimal("9.99")),
          new FruitBasket("apple", 10, new BigDecimal("5.99")),
          new FruitBasket("banana", 10, new BigDecimal("19.99")),
          new FruitBasket("apple", 20, new BigDecimal("9.99")));

  private FruitBasketConstants() {
    throw new IllegalStateException("Utility class");
  }
}
