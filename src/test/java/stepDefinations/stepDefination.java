package stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddBooking;
import Resources.APIResources;
import Resources.TestDataBuild;
import Resources.Utils;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Date;

public class stepDefination extends Utils{
	RequestSpecification req;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String token;
	static String bookingId;
	AddBooking actualBooking = new AddBooking();
	//Scenario: Verify if login has been successfull or not and extract the token.
	
	@Given("Logs into the website with the credentials")
	public void logs_into_the_website_with_the_credentials() throws IOException {
		req=given().spec(requestSpecification()).body(data.getLoginPayload());
	}

	@When("User calls the GetTokenAPI")
	public void user_calls_the_GetTokenAPI() {
	    // Write code here that turns the phrase above into concrete actions
		APIResources resourceAPI=APIResources.valueOf("LoginAPI");
		System.out.println(resourceAPI.getResource());
		resspec =new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		response = req.when().post(resourceAPI.getResource())
        		.then().spec(resspec).extract().response();
		token = getJsonPath(response,"token");
		System.out.println(token);
		
	}

	@Then("The API should be hit with success code {int} and the token should be saved")
	public void the_API_should_be_hit_with_success_code_and_the_token_should_be_saved(Integer int1) {
		assertEquals(response.getStatusCode(),200);
	}
	
	
	//Scenario: Verify if a new booking is created and validate its success
	@Given("Add the payload with requisite details")
	public void add_the_payload_with_requisite_details() throws IOException {
		req=given().spec(requestSpecification())
				.header("Cookie","token="+token+"")
				.body(data.addBooking("nihar","aniruddha",1248,false,"Interplanetary Shuttling"));
	}
	
	@When("User calls the CreateBookingAPI")
	public void user_calls_the_create_booking_api() {
		APIResources resourceAPI=APIResources.valueOf("CreateBookingAPI");
		System.out.println(resourceAPI.getResource());
		resspec =new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		response = req.when().post(resourceAPI.getResource())
        		.then().extract().response();
		
	}
	
	@Then("API should be hit with success code and BookingID should be captured")
	public void api_should_be_hit_with_success_code_and_booking_id_should_be_captured() {
		//assertEquals(response.getStatusCode(),200);
		bookingId = getJsonPath(response,"bookingid");
		System.out.println(bookingId);
	}
	@Then("Firstname and lastname should be equal.")
	public void firstname_and_lastname_should_be_equal() {
	    String actualFname = getJsonPath(response,"firstname");
	    String expectedFname = actualBooking.getFirstName();
	    assertEquals(actualFname,expectedFname);
	    
	    String actualLname = getJsonPath(response,"lastname");
	    String expectedLname = actualBooking.getLastName();
	    assertEquals(actualLname,expectedLname);
	    
	}
	
	@Then("retrieve the details of the booking")
	public void retrieve_the_details_of_the_booking() throws IOException {
		
		resspec =new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		String response = given().spec(requestSpecification())
				.when().get("/booking/"+bookingId+"")
        		.then().spec(resspec).extract().response().asString();
		System.out.println(response);
	}

	
	//DeleteBooking Validate
	@Given("BookingID to be deleted is to be prepped")
	public void booking_id_to_be_deleted_is_to_be_prepped() throws IOException {
		req=given().log().all().spec(requestSpecification()).header("Cookie","token="+token+"");
	}
	@When("API should be hit with token")
	public void api_should_be_hit_with_token() {
		resspec =new ResponseSpecBuilder().expectStatusCode(201)
				.build();
		response = req.when().delete("/booking/"+bookingId+"")
        		.then().log().all().extract().response();
	}
	@Then("Verify if success")
	public void verify_if_success() {
		assertEquals(response.getStatusCode(),201);
	}
	
	//UpdateBooking
	@When("User calls the UpdateBookingAPI with bookingid {string}")
	public void user_calls_the_update_booking_api_with(String string) {
		bookingId=string;
		resspec =new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();
		String response1 = req.when().put("/booking/"+bookingId+"")
        		.then().extract().response().asString();
	}



	



}
