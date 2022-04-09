package com.fruitbasket.services;

import com.fruitbasket.dto.response.FruitBasketResponse;
import java.math.BigDecimal;

public interface FruitBasketService {

  FruitBasketResponse getAllFruitBasket();

  BigDecimal calcCostByFruitNameAndQuantity(String fruitName, int quantity);
}
