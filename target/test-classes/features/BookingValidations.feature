Feature: Testing the Booking Website with basic CRED operations

@Login
Scenario: Verify if login has been successfull or not and extract the token.
	Given Logs into the website with the credentials
	When User calls the GetTokenAPI 
	Then The API should be hit with success code 200 and the token should be saved

@CreateBooking
Scenario: Verify if a new booking is created and validate its success
	Given Add the payload with requisite details
	When User calls the CreateBookingAPI
	Then API should be hit with success code and BookingID should be captured
	And Firstname and lastname should be equal.
	And retrieve the details of the booking

@DeleteBooking
Scenario: Delete Booking
	Given BookingID to be deleted is to be prepped
	When API should be hit with token
	Then Verify if success

@UpdateBooking
Scenario: Verify if the booking is updated and validate its success
	Given Add the payload with requisite details
	When User calls the UpdateBookingAPI with bookingid "1248"
	And retrieve the details of the booking


	
	