package Resources;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import pojo.AddBooking;

public class demoTest extends Utils {

	public static void main(String[] args) throws IOException {
		RequestSpecification req;
		ResponseSpecification resspec;
		APIResources resourceAPI=APIResources.valueOf("CreateBookingAPI");
		System.out.println(resourceAPI.getResource());
		resspec =new ResponseSpecBuilder().expectStatusCode(500)
				.build();
		String response = given().spec(requestSpecification()).queryParam("firstname", "nihar")
				.queryParam("lastname", "aniruddha")
				.when().post(resourceAPI.getResource())
        		.then().spec(resspec).extract().response().asString();
		System.out.println(response);
	}

}
