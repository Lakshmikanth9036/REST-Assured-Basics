Feature: Test 

Background: User generates token for Authorisation 
	Given I am an authorized user 

#Scenario: User calls web service to get a book by its ISBN 
#	Given a book exists with an isbn of "9781449325862" 
#	When a user retrieves the book by isbn 
#	Then the status code is 200 
#	And response includes the following in any order 
#		| title      	| Git Pocket Guide        |
#		| publisher   | O'Reilly Media		  |
#		| pages       | 234             		  |
		
		
#eae7b590-3195-4509-8755-f2bc7a5312db
@smoke
Scenario: the Authorized user can Add and Remove a book. 
	Given A list of books are available 
	When I add a book to my reading list 
	Then The book is added 
	When I remove a book from my reading list 
	Then The book is removed