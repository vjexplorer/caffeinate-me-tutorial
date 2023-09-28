Feature: Order a coffee
  In order to save time when I pick up a coffee in the morning
  As a coffee lover
  I want to be able to order a coffee in advance

  Background:
    Given Cathy is a CaffeinateMe customer

  Scenario: Buyer orders a coffee when close to the coffee shop
    Given Cathy is 100 meters from the coffee shop
    When Cathy orders a "large cappuccino"
    Then Barry should receive the order
    And Barry should know that the coffee is Urgent

  Scenario: Buyer orders a coffee when far from the coffee shop
    Given Cathy is 300 meters from the coffee shop
    When Cathy orders a "regular latte"
    Then Barry should receive the order
    And Barry should know that the coffee is Normal

  Scenario: Buyers can add a comment with their order
    When Cathy orders a "large cappuccino" with a comment "Non Dairy and Double sugar"
    Then Barry should receive the order
    And the order should have the comment "Non Dairy and Double sugar"
