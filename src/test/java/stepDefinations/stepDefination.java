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
import Resources.APIResources;
import Resources.TestDataBuild;
import Resources.Utils;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

public class stepDefination extends Utils{
	RequestSpecification req;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();
	static String token;
	
	
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


}
