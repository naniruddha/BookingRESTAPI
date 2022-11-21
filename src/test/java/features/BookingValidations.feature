Feature: Testing the Booking Website with basic CRED operations

Scenario: Verify if login has been successfull or not and extract the token.
	Given Logs into the website with the credentials
	When User calls the GetTokenAPI 
	Then The API should be hit with success code 200 and the token should be saved