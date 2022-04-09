package com.fruitbasket.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FruitBasket {

  private String fruitName;
  private int quantity;
  private BigDecimal fruitPrice;
}
