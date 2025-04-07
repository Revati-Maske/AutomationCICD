@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file
  
 Background:
 Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: positive test of submitting the order
    Given Logged in with <userName> and <password>
    When  add product <prodName> to cart
    And Checkout <prodName> and submit the order
    Then "Thankyou for the order." message should display on confirmation page

    Examples: 
      |userName              |password  |prodName    |
      |neetamane11@gmail.com |Neeta@113 |ZARA COAT 3 |
      
