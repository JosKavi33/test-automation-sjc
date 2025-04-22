Feature: Verify the product page and its details

  Background: Precondition Feature
    Given The user navigates to the Products page
    Then The Products page loads correctly

  Scenario Outline: Verify that a single product's details are correct
    When I check the details of "<productName>" with price <productPrice>

    Examples:
      | productName          | productPrice |
      | Stylish Dress        | 1500         |
      | Summer White Top     | 400          |
      | Fancy Green Top      | 700          |
      | Madame Top For Women | 1000         |
