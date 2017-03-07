@recipes 
Feature: Testing Quantum on a native app 


Scenario: Adding a recipe 
	Given I am on the home page 
	And I navigate to add another recipe 
	When I add a new recipe name "Blabla" 
	When I navigate to edit recipe 
	And I choose category 
	#	And I add ingredients with the following data 
	#		| name   | amount             |
	#		| Aslak  | aslak@cucumber.io  |
	And I choose a photo 
	And I navigate back to home page 
	Then I expect the new recipe named "Blabla" to be added to the top of the list
	And I delete recipe in position number "1"
	Then I expect recipe name "Blabla" to be deleted 
	Then I close application by name "Recipes" 
	
	#@tag2
	#Scenario Outline: Title of your scenario outline
	#Given I want to write a step with <name>
	#When I check for the <value> in step
	#Then I verify the <status> in step
	
	#Examples:
	#| name  |value | status |
	#| name1 |  5   | success|
	#| name2 |  7   | Fail   |
