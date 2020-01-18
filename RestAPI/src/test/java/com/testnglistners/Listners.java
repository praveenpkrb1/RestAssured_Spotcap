package com.testnglistners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.readjsondata.ParseJSON;
import com.reporting.ReportGeneration;

public class Listners implements ITestListener,IInvokedMethodListener{

	ParseJSON json=new ParseJSON();
	
	//Before Test 
	public void onTestStart(ITestResult result) {
		System.out.println("on test start");
		
	}

	//on every test case pass
	public void onTestSuccess(ITestResult result) {
		
		System.out.println("on test sucess");
	}

	//on every test case failure
	public void onTestFailure(ITestResult result) {
		System.out.println("on test failure");
		
	}

	//on every test skip
	public void onTestSkipped(ITestResult result) {
		
		System.out.println("on test skip");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}

	
	//Before suite
	public void onStart(ITestContext context) {
		
		System.out.println("on Suite before");
		
		
		json.parseJSONFile();
		
		ReportGeneration.intiateReport();
	}

	//after suite
	public void onFinish(ITestContext context) {
		

		System.out.println("on Suite After");
		
		ReportGeneration.flushReport();
		
	}

	//Before Method
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		

		System.out.println("on method before");
		
		System.out.println(testResult.getInstanceName().substring(testResult.getInstanceName().lastIndexOf(".")+1));
		ParseJSON.crntclass=testResult.getInstanceName().substring(testResult.getInstanceName().lastIndexOf(".")+1);
		
		ReportGeneration.startTest(ParseJSON.crntclass);
	}

	//after method
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
		

		System.out.println("on method  after");
		
		ReportGeneration.endTest();
	}

}
