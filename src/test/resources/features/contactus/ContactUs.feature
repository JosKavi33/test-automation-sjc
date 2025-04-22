Feature: ContactUsPage

  Background: Feature Precondition
    Given The user navigates to the Contact Us page

  @critical @regression @smoke
  Scenario: Verify that the Contact Us page loads correctly
    Then The user should see the Contact Us page loaded

  @normal @regression
  Scenario: Send a message through the Contact Us form
    And The user fills and submits the Contact Us form
    Then The user should see a success message
