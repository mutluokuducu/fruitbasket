package com.fruitbasket.services;

import static com.fruitbasket.constant.FruitBasketConstants.FRUIT_BASKETS;
import static com.fruitbasket.exeption.ErrorType.FRUIT_DOES_MORE_THAN_QUANTITY;
import static com.fruitbasket.exeption.ErrorType.FRUIT_DOES_NOT_EXIST;

import com.fruitbasket.dto.FruitBasket;
import com.fruitbasket.dto.response.FruitBasketResponse;
import com.fruitbasket.exeption.FruitsServiceException;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FruitBasketServiceImpl implements FruitBasketService {
  private static final Logger LOGGER = Logger.getLogger(FruitBasketServiceImpl.class.getName());

  @Override
  public FruitBasketResponse getAllFruitBasket() {

    return FruitBasketResponse.builder().fruitBasketList(FRUIT_BASKETS).build();
  }

  @Override
  public BigDecimal calcCostByFruitNameAndQuantity(String fruitName, int quantity) {
    BigDecimal totalPrice;
    FruitBasket minPriceFruit;
    boolean isPresent = isFruitPresent(fruitName);
    if (!isPresent) {
      String message = MessageFormat.format(" {0} does not exist", fruitName);
      LOGGER.log(Level.WARNING, message);
      throw new FruitsServiceException(FRUIT_DOES_NOT_EXIST);
    } else {

      minPriceFruit =
          FRUIT_BASKETS.stream()
              .filter(x -> x.getFruitName().equalsIgnoreCase(fruitName))
              .min(Comparator.comparing(FruitBasket::getFruitPrice))
              .orElseThrow(NoSuchElementException::new);

      if (quantity > minPriceFruit.getQuantity()) {
        String message = MessageFormat.format(" {0} is more quantity", fruitName);
        LOGGER.log(Level.WARNING, message);
        throw new FruitsServiceException(FRUIT_DOES_MORE_THAN_QUANTITY);
      } else {
        totalPrice = minPriceFruit.getFruitPrice().multiply(BigDecimal.valueOf(quantity));
      }
    }
    return totalPrice;
  }

  private boolean isFruitPresent(String fruitName) {
    return FRUIT_BASKETS.stream().anyMatch(p -> p.getFruitName().equalsIgnoreCase(fruitName));
  }
}
