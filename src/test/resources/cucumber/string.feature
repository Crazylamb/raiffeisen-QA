
  Feature: Using cart

    Scenario: Add products to the cart and delete them
      When Go to the shop
      And Add "5" products to the cart
      And Open the cart and delete them
      Then The cart is empty