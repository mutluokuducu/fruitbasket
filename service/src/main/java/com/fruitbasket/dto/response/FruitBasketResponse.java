package com.fruitbasket.dto.response;

import com.fruitbasket.dto.FruitBasket;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FruitBasketResponse {

  private List<FruitBasket> fruitBasketList;
}
