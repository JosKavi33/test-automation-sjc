Feature: Shopping Cart Page

  Background: Feature Precondition
    Given The user goes to the ShoppingCart page with a clean state

  Scenario: Verify that the product links in the cart are correct
    When The user clicks on the cart button
    Then The user should see all product links displayed correctly
