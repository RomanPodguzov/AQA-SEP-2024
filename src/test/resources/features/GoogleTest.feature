Feature: My Feature

  @severity=critical
  Scenario Outline: Search for Ben
    Given Request 3 users from randomuser.me as "random_group"
    Given I pick name <celebrityName> as "some alias"
    Given Load google page
    Given Accept Google cookies if present
    When I google for "some alias"
    Then I can see 3 search results for name "some alias"
    Examples:
      | celebrityName |
      | "Ben Affleck" |

  @severity=blocked
  Scenario Outline: Search for Ben
    Given Request 3 users from randomuser.me as "random_group"
    Given I pick name <celebrityName> as "some alias"
    Given Load google page
    Given Accept Google cookies if present
    When I google for "some alias"
    Then I can see 3 search results for name "some alias"
    Examples:
      | celebrityName   |
      | "Margot Robbie" |

  @severity=trivial
  Scenario Outline: Search for Ben
    Given Request 3 users from randomuser.me as "random_group"
    Given I pick name <celebrityName> as "some alias"
    Given Load google page
    Given Accept Google cookies if present
    When I google for "some alias"
    Then I can see 3 search results for name "some alias"
    Examples:
      | celebrityName  |
      | "Nicolas Cage" |