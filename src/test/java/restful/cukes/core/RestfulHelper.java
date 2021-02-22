package restful.cukes.core;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class RestfulHelper {

	public static Response call(String endPoint, Header header, Object requestBody, Method method) {
		if (RestAssured.baseURI == null) {
			throw new IllegalArgumentException("Define baseURI");
		}
		Response response = null;
		if (method.equals(Method.POST)) {
			response = RestAssured.given().header(header).body(requestBody).when().post(endPoint).then().extract()
					.response();

		} else if (method.equals(Method.GET)) {
			response = RestAssured.given().header(header).when().get(endPoint).then().extract().response();
		} else if (method.equals(Method.PUT)) {
			response = RestAssured.given().header(header).body(requestBody).when().put(endPoint).then().extract()
					.response();
		} else if (method.equals(Method.DELETE)) {
			response = RestAssured.given().header(header).delete(endPoint).then().extract().response();
		}

		return response;

	}
}
