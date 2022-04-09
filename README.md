# Fruit Basket - Java Spring RESTFULL API

## How to build
```./gradlew clean build```

##  Test Details:
###How to test
```./gradlew test```

###Unit test:
   ``` * Service test```
   ``` * Controller test```
###Component test

## Swagger URL

```http://localhost:8080/swagger-ui.html```

### Docker File:
``` Dockerfile ```
## API Description

##### Base root url = "/api/v1/";

##### Get Get all Fruit Baskets = "allFruitBaskets";

##### Get Calculate FruitBaskets= "calcCostByFruitNameAndQuantity/{ fruitName}/{quantity}";

## Exercises

We have FruitBasket with attributes a)      fruitName b)      quantity c)      fruitPrice

Below is the baskets available at the shop.

java.util.List<FruitBasket> fruitBaskets = Arrays.asList(
new FruitBasket("apple", 10, new BigDecimal("9.99")), new FruitBasket("banana", 20, new BigDecimal("
19.99")), new FruitBasket("orange", 10, new BigDecimal("29.99")), new FruitBasket("watermelon", 10,
new BigDecimal("29.99")), new FruitBasket("papaya", 20, new BigDecimal("9.99")), new FruitBasket("
apple", 10, new BigDecimal("9.99")), new FruitBasket("banana", 10, new BigDecimal("19.99")), new
FruitBasket("apple", 20, new BigDecimal("9.99")) );

### Exercise

Write a RESTful API endpoint to retrieve a list of all people with a particular surname GET
//allFruitBaskets : Add Rest get endpoint to fetch all fruit baskets java.util.List<FruitBasket>.
GET /calcCostByFruitNameAndQuantity/{ fruitName}/{quantity} return BigDecimal
