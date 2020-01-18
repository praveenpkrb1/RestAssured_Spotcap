package com.putrequest;

import java.util.Date;

import com.commonutils.CommonUtils;
import com.commonutils.Statuscodes;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PutRequests extends CommonUtils{
	
	public void updateCustomer(String custID)
	{
		// get time difference
				Date d1 = getCurrentTime();

				// define base URL

				RestAssured.baseURI = getData("BaseURI");

				// use the rest assured annotation based syntax

				Response response = RestAssured.given().body(parsePayloadJSONFile("UpDateCustomer"))

						.when().put(getData("Customer_Resources")+custID).					
						

						then().assertThat().statusCode(Statuscodes.PUT.getValue()).log().all().

						extract().response();
				
				Date d2 = getCurrentTime();


				// jsonpath

				JsonPath path = getJsonObject(response);
				
				try {
					String ExpectedFirstName=getPayloadJSONObject("UpDateCustomer").get("firstname").toString();
					String ExpectedLastName=getPayloadJSONObject("UpDateCustomer").get("lastname").toString();
					
					if(path.get("firstname").equals(ExpectedFirstName)) {
						logEvent("Pass", "First Name is validated sucessfully");
					}else
					{
						logEvent("Fail", "First Name is validated sucessfully");
					}
					
					if(path.get("lastname").equals(ExpectedLastName)) {
						logEvent("Pass", "Last Name is validated sucessfully");
					}else
					{
						logEvent("Fail", "Last Name is validated sucessfully");
					}
					
					logEvent("Pass", "Put Operation performed sucessfully for updating customer");
					logEvent("info", response.getBody().prettyPrint());

				} catch (Exception e) {
					logEvent("Fail", "Unable to extract Expected Results for Put Request ");
					logEvent("Fail", "The Json Response is " + e.getMessage());
				}

				
				

	}

}
