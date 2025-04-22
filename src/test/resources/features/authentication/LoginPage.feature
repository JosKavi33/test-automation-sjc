Feature: Login Page

  Background: The user is on the Login page
    Given The user goes to the Login page

  @regression @smoke
  Scenario: Validate that the user logs in successfully
    Then The user should be logged in and see the correct page
