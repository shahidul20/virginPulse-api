package booking.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.github.javafaker.Faker;

import domain.booking.create.CreateBooking;

public class TestDataCreator {

	public static CreateBooking getCreateBookingData() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		String firstName = Faker.instance().name().firstName();
		String lastName = Faker.instance().name().lastName();
		int numberBetween = Faker.instance().number().numberBetween(1, 0);
		String checkInDate = LocalDateTime.now().format(dateTimeFormatter);
		String checkOutDate = LocalDateTime.now().plusDays(1).format(dateTimeFormatter);
		String specialNeed = Faker.instance().beer().hop().toString();

		CreateBooking booking = new CreateBooking();
		booking.setFirstname(firstName);
		booking.setLastname(lastName);
		booking.setTotalprice(numberBetween);
		booking.setDepositpaid(true);
		booking.setAdditionalneeds(specialNeed);
		booking.getBookingdates().setCheckin(checkInDate);
		booking.getBookingdates().setCheckout(checkOutDate);
		return booking;
	}
}
