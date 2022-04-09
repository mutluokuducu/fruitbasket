package com.fruitbasket.controller;

import static com.fruitbasket.constant.FruitBasketConstants.ALL_FRUITS_URL;
import static com.fruitbasket.constant.FruitBasketConstants.CALC_FRUITS_URL;
import static com.fruitbasket.constant.FruitBasketConstants.FRUIT_BASKETS;
import static com.fruitbasket.exeption.ErrorType.FRUIT_DOES_MORE_THAN_QUANTITY;
import static com.fruitbasket.exeption.ErrorType.FRUIT_DOES_NOT_EXIST;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fruitbasket.dto.response.FruitBasketResponse;
import com.fruitbasket.exeption.FruitsServiceException;
import com.fruitbasket.exeption.GlobalExceptionHandler;
import com.fruitbasket.services.FruitBasketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class CreditCardControllerTest {

  private MockMvc mockMvc;

  @Mock private FruitBasketService fruitBasketService;

  @InjectMocks private FruitBasketController fruitBasketController;

  @BeforeEach
  public void init() {
    mockMvc =
        MockMvcBuilders.standaloneSetup(fruitBasketController)
            .setControllerAdvice(new GlobalExceptionHandler())
            .build();
  }

  @Test
  void shouldReturnBasketFromService() throws Exception {
    FruitBasketResponse fruitBasketResponse =
        FruitBasketResponse.builder().fruitBasketList(FRUIT_BASKETS).build();

    when(fruitBasketService.getAllFruitBasket()).thenReturn(fruitBasketResponse);
    this.mockMvc
        .perform(get(ALL_FRUITS_URL))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.fruitBasketList[0].fruitName").value("apple"))
        .andExpect(jsonPath("$.fruitBasketList[0].quantity").value("10"))
        .andExpect(jsonPath("$.fruitBasketList[0].fruitPrice").value("1.99"));
  }

  @Test
  void fruits_shouldReturnFruitDoesNotExist() throws Exception {

    doThrow(new FruitsServiceException(FRUIT_DOES_NOT_EXIST))
        .when(fruitBasketService)
        .calcCostByFruitNameAndQuantity("apples", 10);

    this.mockMvc
        .perform(get(CALC_FRUITS_URL, "apples", 10).contentType(APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isNotFound());
  }

  @Test
  void fruits_shouldReturnFruitDoesNotExist_whenDoestNotExist1() throws Exception {

    doThrow(new FruitsServiceException(FRUIT_DOES_MORE_THAN_QUANTITY))
        .when(fruitBasketService)
        .calcCostByFruitNameAndQuantity("apple", 100);

    this.mockMvc
        .perform(get(CALC_FRUITS_URL, "apple", 100).contentType(APPLICATION_JSON_UTF8_VALUE))
        .andExpect(status().isNotAcceptable());
  }
}
