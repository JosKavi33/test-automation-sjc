Feature: HomePage

  Background: Feature Precondition
    Given The user goes to the HomePage

  @critical @regression @smoke
  Scenario: Verify the HomePage UI
    Then The user verifies that the page UI is correct