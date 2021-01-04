#Feature: RestApi 
#
#Scenario: Get Request 
#	Given I have an endpoint url "http://dummy.restapiexample.com/api/v1/employees" 
#	When I hit the get request 
#	Then I should validate the response code and response body
#
#Scenario: Post Request 
#	Given I have an endpoint url "http://dummy.restapiexample.com/api/v1/create" 
#	When I hit the post request 
#	Then I should validate the response code and success message after Post request 
#	
#Scenario: Put Request 
#	Given I have an endpoint url "http://dummy.restapiexample.com/api/v1/update/2046" 
#	When I hit the request and update the user name 
#	Then I should validate the response code and success message after Put request 
#	
#Scenario: Get Request 
#	Given I have an endpoint url "http://dummy.restapiexample.com/api/v1/employees" 
#	When I hit the get request 
#	Then I should validate the response code and print all the employee names from the data set 
#	Then I should validate employee salary associated with employee name from data set 
#	Then I should validate all the headers from the response 
#
#Scenario Outline: Get Request 
#	Given I have an endpoint url "https://reqres.in/api/users" 
#	When I hit the request for "page" 
#	Then I should validate the response code and print all the email ids from the data set 
#	Then I should validate last name associated with email id from data set 
#	Then I should validate all the users headers from the response 
#	
#	Examples: 
#		|page|
#		|2|