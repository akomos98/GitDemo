
@tag
Feature:	Error validation
  I want to use this template for my feature file



  @ErrorValidation
  Scenario Outline: Title of your scenario outline
  	Given I landed on Ecommerce Page
    And Logged in with username <name> and password <password> 
    Then "Incorrect email or password." message is displayed on ConfirmationPage

    Examples: 
      | name  									| password 		| 
      | semmame@gmail.com 			| Au1234kurt  |  
