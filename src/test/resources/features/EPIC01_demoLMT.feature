Feature: EPIC-01 LMT webshop page

  @TC1
  Scenario: Test automation demostration
    Given user navigate to webshop "Home" page
    Then user sees main product options:
      | Telefoni      |
      | Televizori    |
      | Audio         |
      | Planšetes     |
      | Datortehnika  |
      | Viedpulksteņi |
      | Viedierīces   |
    When user search product "GARMIN"
    When user clicks on first search result
    When user add to cart product and go to cart

