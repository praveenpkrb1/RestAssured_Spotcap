package com.testcases;

import org.testng.annotations.Test;

import com.commonutils.CommonUtils;
import com.getrequest.GetRequests;

public class Validate_Tc_01_Get_Categories extends CommonUtils{
	
	@Test(description="Get all the Categories from the shop")
	
	public void Tc_01_Get_Categories()
	{
		GetRequests get=new GetRequests();
		
		get.getCategories();
		
	}

}
