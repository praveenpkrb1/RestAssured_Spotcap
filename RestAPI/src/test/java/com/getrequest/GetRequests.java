package com.getrequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;

import com.commonutils.CommonUtils;
import com.commonutils.Statuscodes;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetRequests extends CommonUtils {

	public void getCategories() {
		// get time difference
		Date d1 = getCurrentTime();

		// define base url
		RestAssured.baseURI = getData("BaseURI");

		// use the rest assured annotation based syntax

		Response response = RestAssured.given()

				.when().get(getData("Resources")).

				then().assertThat().statusCode(Statuscodes.GET.getValue()).log().all().

				extract().response();

		System.out.println(response);

		// jsonpath

		JsonPath path = getJsonObject(response);
		// Get all categories

		System.out.println(response.jsonPath().getList("categories").size());
		try {
			String[] expectedResults = getData("Expected_Results").split(";");
			for (int i = 0; i < response.jsonPath().getList("categories").size(); i++) {

				if (path.get("categories[" + i + "].name").equals(expectedResults[i])) {
					logEvent("Pass",
							"Category Name: " + path.get("categories[" + i + "].name") + " is validated sucessfully");

				} else {
					logEvent("Fail", "Category Name: " + path.get("categories[" + i + "].name")
							+ " is not validated with Expected result :" + expectedResults[i]);

				}

			}
			logEvent("Pass", "Get Operation performed sucessfully for getting categories");
			logEvent("info", response.getBody().prettyPrint());

		} catch (Exception e) {
			logEvent("Fail", "Unable to extract Expected Results from getCategories");
			logEvent("Fail", "The Json Response is " + e.getMessage());
		}

		Date d2 = getCurrentTime();

		// in milliseconds
		long diff = d2.getTime() - d1.getTime();
		long diffSeconds = diff / 1000 % 60;
		System.out.print(diffSeconds + " seconds.");

	}

	public void getCustomerDetails(String customerID) {
		// get time difference
		Date d1 = getCurrentTime();

		// define base url
		RestAssured.baseURI = getData("BaseURI");

		// use the rest assured annotation based syntax

		Response response = RestAssured.given()

				.when().get(getData("Customer_Resources") + customerID).

				then().assertThat().statusCode(Statuscodes.GET.getValue()).log().all().

				extract().response();

		System.out.println(response);

		// jsonpath

		JsonPath path = getJsonObject(response);

		try {
			String ExpectedFirstName = getPayloadJSONObject("CreateCustomer").get("firstname").toString();
			String ExpectedLastName = getPayloadJSONObject("CreateCustomer").get("lastname").toString();

			if (path.get("firstname").equals(ExpectedFirstName)) {
				logEvent("Pass", "First Name is validated sucessfully");
			} else {
				logEvent("Fail", "First Name is validated sucessfully");
			}

			if (path.get("lastname").equals(ExpectedLastName)) {
				logEvent("Pass", "Last Name is validated sucessfully");
			} else {
				logEvent("Fail", "Last Name is validated sucessfully");
			}
			logEvent("Pass", "Get Operation performed sucessfully for getting customer details");
			logEvent("info", response.getBody().prettyPrint());

		} catch (Exception e) {
			logEvent("Fail", "Unable to extract Expected Results From getCustomerDetails");
			logEvent("Fail", "The Json Response is " + e.getMessage());
		}

		Date d2 = getCurrentTime();

		// in milliseconds
		long diff = d2.getTime() - d1.getTime();
		long diffSeconds = diff / 1000 % 60;
		System.out.print(diffSeconds + " seconds.");
		// logEvent("info", response.getBody().prettyPrint());
		System.out.println(response.getBody().prettyPrint());
	}

	public String fetchOrderDetails() {

		String order_id = "";
		// get time difference
		Date d1 = getCurrentTime();

		// define base url
		RestAssured.baseURI = getData("BaseURI");

		// use the rest assured annotation based syntax

		Response response = RestAssured.given()

				.when().get(getData("get_orders_resource")).

				then().assertThat().statusCode(Statuscodes.GET.getValue()).log().all().

				extract().response();

		System.out.println(response);

		// jsonpath

		JsonPath path = getJsonObject(response);

		try {
			System.out.println(response.jsonPath().getList("orders").size());
			if (response.jsonPath().getList("orders").size() > 0) {

				System.out.println(response.getBody().prettyPrint());
				System.out.println(path.get("orders[0].order_url"));
				order_id = path.get("orders[0].order_url").toString()
						.substring(path.get("orders[0].order_url").toString().lastIndexOf("/") + 1);
			} else {
				throw new Exception("No Order details found on the system");
			}

		} catch (Exception e) {
			logEvent("Fail", "Unable to extract order results from fetchOrderDetails");
			logEvent("Fail", "The Json Response is " + e.getMessage());
		}

		Date d2 = getCurrentTime();

		// in milliseconds
		long diff = d2.getTime() - d1.getTime();
		long diffSeconds = diff / 1000 % 60;
		System.out.print(diffSeconds + " seconds.");
		System.out.println(response.getBody().prettyPrint());
		logEvent("Pass", "Get Operation performed sucessfully for getting orderdetails");
		logEvent("info", response.getBody().prettyPrint());
		return order_id;
	}

}
