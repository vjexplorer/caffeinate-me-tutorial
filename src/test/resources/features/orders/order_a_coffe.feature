Feature: Order a coffee
  In order to save time when I pick up a coffee in the morning
  As a coffee lover
  I want to be able to order a coffee in advance

  Background:
    Given Cathy is a CaffeinateMe customer

  Rule: Buyer orders an item closer to store, order is considered as Urgent
  Example: Buyer orders a coffee when close to the coffee shop
    Given Cathy is 100 meters from the coffee shop
    When Cathy orders a "large cappuccino"
    Then Barry should receive the order
    And Barry should know that the coffee is Urgent

  Example: Buyer orders a coffee when far from the coffee shop
    Given Cathy is 300 meters from the coffee shop
    When Cathy orders a "regular latte"
    Then Barry should receive the order
    And Barry should know that the coffee is Normal

   Rule: Buyer can add additional instructions when they order an item
   Example: Buyers can add a comment with their order
    When Cathy orders a "large cappuccino" with a comment "Non Dairy and Double sugar"
    Then Barry should receive the order
    And the order should have the comment "Non Dairy and Double sugar"


  Rule: Buyers can order many items in the same order
    Example: A buyer orders two items in the same order
      When Cathy places an order for the following items:
        | Product          | Quantity |
        | Large cappuccino | 1        |
        | Espresso         | 2        |
      Then Barry should receive the order
      And the order should contain 2 line items
      And the order should contain the following products:
        | Large cappuccino |
        | Espresso         |
