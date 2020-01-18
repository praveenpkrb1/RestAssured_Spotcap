package com.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.commonutils.CommonUtils;
import com.deleterequest.DeleteRequests;
import com.getrequest.GetRequests;
import com.postrequest.PostRequests;
import com.putrequest.PutRequests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Validate_Tc_02_Get_Orders_E2E extends CommonUtils {
@Test
public void postData()
{
	PostRequests post=new PostRequests();
	//create a new customer
	JsonPath path=post.createPostRequest("CreateCustomer","CreateCustomer", "Customer_Resources",null);
	if(!(path==null)) {
		
		logEvent("Pass", "User Created With the First Name "+path.get("firstname")+" Last name "+path.get("lastname"));
		customer_ID=path.get("customer_url").toString().substring(path.get("customer_url").toString().lastIndexOf("/")+1);
		
		GetRequests get=new GetRequests();
		
		//get the customer details
		get.getCustomerDetails(customer_ID);
		
		//modify the existing customer
		PutRequests put=new PutRequests();
		put.updateCustomer(customer_ID);
		
			
		//create an order
		JsonPath postPath=post.createPostRequest("Create Order",null, "Orders_Resources",customer_ID);
		System.out.println(postPath);
		order_ID=postPath.get("items_url").toString().replaceAll("[^0-9]", "").trim();
		//delete an order
		DeleteRequests delete=new DeleteRequests();
		delete.deleteRequest("Delete Order","Delete_Orders_Resources",order_ID);
		//delete a customer
		delete.deleteRequest("Delete Customer","Delete_Customer_Resources",customer_ID);
		
	}
}
}
