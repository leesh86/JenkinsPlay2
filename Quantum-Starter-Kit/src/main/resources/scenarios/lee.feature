@lee
Feature: Testing Quantum on a hybrid app

@lee
Scenario: searching FMG app
#Given I wait "5" seconds for elements to appear
Given I am on the login page
And I login with user name "client1@test.fmglobal.com" and password "password"
#Then I expect to be on the accounts page
#When I search for "general" account
#And I navigate to general account
#Then I expect to be in general account page
Then I close application by name "MyRisk Mobile"

#@tag2
#Scenario Outline: Title of your scenario outline
#Given I want to write a step with <name>
#When I check for the <value> in step
#Then I verify the <status> in step

#Examples:
    #| name  |value | status |
    #| name1 |  5   | success|
    #| name2 |  7   | Fail   |
