package restful.cukes.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Cukes {

	@Before
	public void init() {
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
	}

	@After
	public void tearDown() {
	}

}
