package com.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.commonutils.CommonUtils;
import com.getrequest.GetRequests;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Validate_Tc_01_Get_Categories extends CommonUtils{
	
	@Test(description="Get all the Categories from the shop")
	
	public void Tc_01_Get_Categories()
	{
		GetRequests get=new GetRequests();
		
		get.getCategories();
		
	}

}
