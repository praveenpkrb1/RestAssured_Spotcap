package com.reporting;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportGeneration {
	
	public static ExtentReports extent;
	
	public static ExtentTest test;
	
	
	public static void intiateReport()
	{
		extent=new ExtentReports(createResultsFolder()+"\\Test Summary.html");		
		
	}
	
	public static void startTest(String testcasename)
	{
		test=extent.startTest(testcasename);
	}
	
	public static void endTest()
	{
		extent.endTest(test);
	}
	
	public static void logEvent(String status,String description)
	{
		switch (status.toLowerCase()) {
		case "pass":
			test.log(LogStatus.PASS, description);
			break;
		case "fail":
			test.log(LogStatus.FAIL, description);
			break;
		case "warning":
			test.log(LogStatus.WARNING, description);
			break;
		case "info":
			test.log(LogStatus.INFO, description);
			break;

		default:
			test.log(LogStatus.FAIL, "Invalid LOG EVENT");
			break;
		}
	}
	
	public static void flushReport()
	{
		extent.flush();
		extent.close();
	}
	
	
	
	
	
	public static String createResultsFolder()
	{
		SimpleDateFormat sdf=new SimpleDateFormat("MM-dd-yyyy");
		
		Date date=new Date();
		
		String crntdate=sdf.format(date).toString();
		
		String resultpath=System.getProperty("user.dir")+"\\Results\\"+crntdate;
		
		File file=new File(resultpath);
		
		if(!file.exists())
		{
			file.mkdirs();
		}
		return resultpath;
		
	}
	

}
