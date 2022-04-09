package com.fruitbasket.service;

import static com.fruitbasket.exeption.ErrorType.FRUIT_DOES_MORE_THAN_QUANTITY;
import static com.fruitbasket.exeption.ErrorType.FRUIT_DOES_NOT_EXIST;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.fruitbasket.dto.response.FruitBasketResponse;
import com.fruitbasket.exeption.FruitsServiceException;
import com.fruitbasket.services.FruitBasketServiceImpl;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreditCardServiceImplTest {

  @InjectMocks private FruitBasketServiceImpl fruitBasketService;

  @Test
  void processFruits_ShouldGetFruitBasketList() {
    FruitBasketResponse fruitBasket = fruitBasketService.getAllFruitBasket();
    assertThat(fruitBasket).isNotNull();
    assertThat(fruitBasket.getFruitBasketList().get(0).getFruitName()).isEqualTo("apple");
    assertThat(fruitBasket.getFruitBasketList().get(0).getQuantity()).isEqualTo(10);
    assertThat(fruitBasket.getFruitBasketList().get(0).getFruitPrice()).isEqualTo("1.99");
  }

  @Test
  void processFruitsCalcByFruitNameAndQuantity_ShouldGetFTotalPrice() {
    BigDecimal totalPrice = fruitBasketService.calcCostByFruitNameAndQuantity("apple", 10);
    assertThat(totalPrice).isEqualTo("19.90");
  }

  @Test
  void processFruits_ShouldThrowException_WhenFruitDoesNotExist() {

    FruitsServiceException exceptionThrown =
        assertThrows(
            FruitsServiceException.class,
            () -> fruitBasketService.calcCostByFruitNameAndQuantity("apples", 10));

    assertThat(exceptionThrown.getErrorType()).isEqualTo(FRUIT_DOES_NOT_EXIST);
  }

  @Test
  void processFruits_ShouldThrowException_WhenFruitQuantityMoreThan() {

    FruitsServiceException exceptionThrown =
        assertThrows(
            FruitsServiceException.class,
            () -> fruitBasketService.calcCostByFruitNameAndQuantity("apple", 100));

    assertThat(exceptionThrown.getErrorType()).isEqualTo(FRUIT_DOES_MORE_THAN_QUANTITY);
  }
}
