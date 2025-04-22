Feature: Products Page

  Background:
    Given The user has an empty shopping cart and navigates to the Products page
    Then The Products page loads correctly

  @critical @regression @smoke
  Scenario: Add multiple products to the shopping cart
    When The user adds 5 products to the shopping cart
    Then The shopping cart should contain 5 products
