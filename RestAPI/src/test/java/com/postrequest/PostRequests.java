package com.postrequest;

import java.util.Date;

import com.commonutils.CommonUtils;
import com.commonutils.Statuscodes;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostRequests extends CommonUtils {

	public JsonPath createPostRequest(String postFor,String payload, String resourceName,String replaceValue) {
		JsonPath path =null;
		String jsonPayload="";
		try {
			
		String resource=getData(resourceName);
		//Get resouce name from testdata
		if(resource.toString().toUpperCase().contains("{REPLACE}")) {
			resource=getData(resourceName).replace("{REPLACE}", replaceValue);
		}
		// Check if payload is required
		if(!(payload==null))
		{
			jsonPayload=parsePayloadJSONFile(payload);
		}
		
		
		
		
		// get time difference
		Date d1 = getCurrentTime();

		// define base url

		RestAssured.baseURI = getData("BaseURI");

		// use the rest assured annotation based syntax

		Response response = RestAssured.given().body(jsonPayload)

				.when().post(resource).

				then().assertThat().statusCode(Statuscodes.POST.getValue()).log().all().

				extract().response();

		Date d2 = getCurrentTime();

		// in milliseconds
		long diff = d2.getTime() - d1.getTime();
		long diffSeconds = diff / 1000 % 60;
		System.out.print(diffSeconds + " seconds.");
		System.out.println(response.getBody().prettyPrint());
		path = getJsonObject(response);
		logEvent("Pass", "Post Operation performed sucessfully for "+postFor);
		logEvent("info", response.getBody().prettyPrint());
		}catch(Exception e) {
			System.out.println("Unable to perform post request for the resource "+resourceName);
			logEvent("fail", "Unable to perform post request for the resource "+resourceName);
			logEvent("info", e.getMessage());
		}
		return path;

	}

}
