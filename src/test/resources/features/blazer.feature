Feature: DEMO ONLINE SHOP

  Scenario: Adding items to the cart
    Given Pablo is on the Blaze home page
    When he navigates to "Laptops" category and adds the following products to the cart
      | Sony vaio i5  |
      | Dell i7 8gb   |
    And he navigates to Cart page and deletes the following products from the cart
      | Dell i7 8gb  |
    And he place the order
    Then receipt matches the order
    And accepts the receipt
