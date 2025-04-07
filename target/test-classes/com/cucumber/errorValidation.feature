
@tag
Feature: Error Validation for Ecommerce website
  I want to use this template for my feature file
  
  @ErrorValidation
  Scenario Outline: Negative test of login functionality
   Given I landed on Ecommerce Page
    When Logged in with <userName> and <password>
    Then "Incorrect email or password." message should displayed

    Examples: 
      |userName              |password  | 
      |neetamane11@gmail.com |Neeta@11  |
 