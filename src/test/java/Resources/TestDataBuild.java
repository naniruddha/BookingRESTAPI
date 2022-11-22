package Resources;

import java.util.Date;

import pojo.AddBooking;
import pojo.LoginPayload;

public class TestDataBuild {
	public LoginPayload getLoginPayload() {
		LoginPayload lp = new LoginPayload();
		lp.setUsername("admin");
		lp.setPassword("password123");
		return lp;
	}
	
	public String addBooking(String fname,String lname,Number totalPrice,Boolean depositPaid,String additionalNeeds)
	{
		AddBooking booking = new AddBooking();
		booking.setFirstName(fname);
		booking.setLastName(lname);
		booking.setTotalPrice(totalPrice);
		booking.setDepositPaid(depositPaid);
		booking.setAdditionalNeeds(additionalNeeds);
		
		return "{\r\n"
				+ "    \"firstname\" : \""+booking.getFirstName()+"\",\r\n"
				+ "    \"lastname\" : \""+booking.getLastName()+"\",\r\n"
				+ "    \"totalprice\" : "+booking.getTotalPrice()+",\r\n"
				+ "    \"depositpaid\" : "+booking.getDepositPaid()+",\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2018-01-01\",\r\n"
				+ "        \"checkout\" : \"2019-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \""+booking.getAdditionalNeeds()+"\"\r\n"
				+ "}";
	}
}
