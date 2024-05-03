Feature: EPIC-01 Open LMT webshop page

  @TC1
  Scenario: Validate the content main product menu
    Given user navigate to webshop "Base" page
    Then user sees main product options:
      | Telefoni      |
      | Televizori    |
      | Audio         |
      | Planšetes     |
      | Datortehnika  |
      | Viedpulksteņi |
      | Viedierīces   |


  @TC2
  Scenario: Validate the content main product menu
    Given user navigate to webshop "Base" page
    Then user sees main product options:
      | Telefoni      |
      | Televizori    |
      | Audio         |
      | Planšetes     |
      | Datortehnika  |
      | Viedpulksteņi |
      | Viedierīces   |