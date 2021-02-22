package restful.cukes.steps;

import java.util.Map;

import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;

import booking.data.TestDataCreator;
import domain.booking.create.CreateBooking;
import domain.booking.create.CreateBookingResponse;
import io.cucumber.java.en.Given;
import io.restassured.http.Header;
import io.restassured.http.Method;
import io.restassured.response.Response;
import restful.cukes.core.RestfulHelper;

public class CreateBookingSteps {

	@Given("I create a booking")
	public void i_create_a_booking() {

		CreateBookingResponse response = RestfulHelper.call("/booking", new Header("Content-type", "application/json"),
				TestDataCreator.getCreateBookingData(), Method.POST).as(CreateBookingResponse.class);

		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(response.getBookingid() != null);
			// Add more assertions as needed
		});

	}

	@Given("I update a booking")
	public void i_update_a_booking() {
		CreateBooking bookingData = TestDataCreator.getCreateBookingData();
		CreateBookingResponse response = RestfulHelper
				.call("/booking", new Header("Content-type", "application/json"), bookingData, Method.POST)
				.as(CreateBookingResponse.class);

		bookingData.setAdditionalneeds("updated");
		Response responseObj = RestfulHelper.call("/booking/" + response.getBookingid(),
				new Header("Content-type", "application/json"), bookingData, Method.PUT);
		// The website doesn't allow this method
		if (responseObj.getStatusCode() != 200) {
			Assert.fail("Invalid server response");
		}
		CreateBookingResponse putResponse = responseObj.as(CreateBookingResponse.class);

		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(putResponse.getBooking().getAdditionalneeds().equals("updated"));
			// Add more assertions as needed
		});
	}

	@Given("I get booking by id")
	public void i_get_book_by_id() {
		CreateBooking bookingData = TestDataCreator.getCreateBookingData();
		CreateBookingResponse response = RestfulHelper
				.call("/booking", new Header("Content-type", "application/json"), bookingData, Method.POST)
				.as(CreateBookingResponse.class);

		Map<?, ?> responseMap = RestfulHelper.call("/booking/" + response.getBookingid(),
				new Header("Content-type", "application/json"), null, Method.GET).as(Map.class);

		SoftAssertions.assertSoftly(softly -> {
			softly.assertThat(responseMap.get("firstname").equals(bookingData.getFirstname()));
			// Add more assertions as needed
		});
	}

	@Given("I delete booking by id")
	public void i_delete_a_booking() {
		CreateBooking bookingData = TestDataCreator.getCreateBookingData();
		CreateBookingResponse response = RestfulHelper
				.call("/booking", new Header("Content-type", "application/json"), bookingData, Method.POST)
				.as(CreateBookingResponse.class);

		Response responseObj = RestfulHelper.call("/booking/" + response.getBookingid(),
				new Header("Content-type", "application/json"), bookingData, Method.DELETE);
		// The website doesn't allow this method
		if (responseObj.getStatusCode() != 200) {
			Assert.fail("Invalid server response");
		}
		// add softAssert
	}
}
