package com.deleterequest;

import java.util.Date;

import com.commonutils.CommonUtils;
import com.commonutils.Statuscodes;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DeleteRequests extends CommonUtils {

	public void deleteRequest(String deleteFor, String resourceName, String replaceValue) {

		String resource = getData(resourceName);
		// Get resouce name from testdata
		if (resource.toString().toUpperCase().contains("{REPLACE}")) {
			resource = resource.replace("{REPLACE}", replaceValue);
		}

		Date d1 = null;
		try {
			// get time difference
			d1 = getCurrentTime();

			// define base url
			RestAssured.baseURI = getData("BaseURI");

			// use the rest assured annotation based syntax

			Response response = RestAssured.given()

					.when().get(resource).

					then().assertThat().statusCode(Statuscodes.DELETE.getValue()).log().all().

					extract().response();

			System.out.println("Delete :" + response);

			// jsonpath

			JsonPath path = getJsonObject(response);
			logEvent("Pass", "Delete Operation Performed Successfully for :" + deleteFor);
			logEvent("info", response.getBody().prettyPrint());

		} catch (Exception e) {
			logEvent("Fail", "Unable to extract Expected Results from Delete operation ");
			logEvent("Fail", "The Json Response is " + e.getMessage());
		}

		Date d2 = getCurrentTime();

		// in milliseconds
		long diff = d2.getTime() - d1.getTime();
		long diffSeconds = diff / 1000 % 60;
		System.out.print(diffSeconds + " seconds.");
	}

}
