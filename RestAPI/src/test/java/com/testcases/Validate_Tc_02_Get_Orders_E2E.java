package com.testcases;

import org.testng.annotations.Test;

import com.commonutils.CommonUtils;
import com.deleterequest.DeleteRequests;
import com.getrequest.GetRequests;
import com.postrequest.PostRequests;
import com.putrequest.PutRequests;

import io.restassured.path.json.JsonPath;

public class Validate_Tc_02_Get_Orders_E2E extends CommonUtils {
@Test
public void postData()
{
	PostRequests post=new PostRequests();
	//create a new customer
	JsonPath path=post.createPostRequest("CreateCustomer", "Customer_Resources",null);
	if(!(path==null)) {
		
		logEvent("Pass", "User Created With the First Name "+path.get("firstname")+" Last name "+path.get("lastname"));
		String customer_Id=path.get("customer_url").toString().substring(path.get("customer_url").toString().lastIndexOf("/")+1);
		
		GetRequests get=new GetRequests();
		
		//get the customer details
		get.getCustomerDetails(customer_Id);
		
		//modify the existing customer
		PutRequests put=new PutRequests();
		put.updateCustomer(customer_Id);
		
		//get the order id
		order_ID=get.fetchOrderDetails();		
		//create an order
		JsonPath postPath=post.createPostRequest("CreateOrder", "Orders_Resources",order_ID);
		System.out.println(postPath);
		//delete an order
		DeleteRequests delete=new DeleteRequests();
		delete.deleteRequest("Delete Order","Delete_Orders_Resources",order_ID);
		//delete a customer
		delete.deleteRequest("Delete Customer","Delete_Customer_Resources",customer_Id);
		
	}
}
}
