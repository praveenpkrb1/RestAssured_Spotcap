package com.readjsondata;

import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.reporting.ReportGeneration;

public class ParseJSON extends ReportGeneration{
	
	public static JSONParser parser;
	
	public static JSONObject jsonobject;
	
	public static JSONArray array;
	
	public static String crntclass;
	
	
	
	/**
	 * @MethodName:parseJSONFile
	 * @Purpose:This method will load the json testdata file
	 * @param:NA
	 */
	public void parseJSONFile()
	{
		System.out.println("Parsing Json File");
		parser=new JSONParser();
		try
		{
			Object object=parser.parse(new FileReader("./Testdata/Testadata.json"));
			jsonobject =(JSONObject)object;
			
			System.out.println("JSON File is parsed sucessfully");
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 
	 */
	public String getData(String property)
	{
		
		
		System.out.println("Reading JSON object to identify the test case "+crntclass);
		String data="";
		try
		{
			array=(JSONArray)jsonobject.get("Testdata");
			
			for(int i=0;i<=array.size()-1;i++)
			{
				JSONObject item=(JSONObject)array.get(i);
				
				String testcase=(String)item.get("TestCaseName");
				
				if(testcase.trim().equals(crntclass))
				{
					data=(String)item.get(property);
					break;
				}
				
				
			}
			
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		if(!data.isEmpty())
		{
			System.out.println("Data is found for the property :"+property+" with value as : "+data);
		}else
		{
			System.out.println("No data is found for the test case : "+crntclass);
			try {
				throw new Exception("No data is found for the test case : "+crntclass);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		
		return data;
	}
	

}
