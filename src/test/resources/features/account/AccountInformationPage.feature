Feature: Account Information Page

  Background: The user is on the SignUp/Login Page
    Given The user goes to the SignUpLogin page

  @critical @regression @smoke
  Scenario: Validate that the account can be created successfully
    When The user fills out the signup form
    And The user fills out the account information form
    Then The user sees the Account Created confirmation
