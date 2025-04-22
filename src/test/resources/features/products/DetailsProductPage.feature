Feature: Product Details Page

  Background: Feature Precondition
    Given The user navigates to the Products page
    Then The Products page loads correctly

  Scenario: Verify the Product Details Page loads correctly
    When The user opens the first product details
    Then The user should see the Product Details page loaded

  Scenario: Submit a product review
    When The user opens the first product details
    And The user submits a product review
    Then The user should see a confirmation or success message
