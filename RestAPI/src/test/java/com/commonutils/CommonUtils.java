package com.commonutils;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.readjsondata.ParseJSON;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CommonUtils extends ParseJSON{
	
	public static String order_ID="";
	public static String customer_ID="";
	
	
	
		
	/**
	 * @MethodName:GetjsonObject
	 * @InputParam:Response Object
	 * @OutputParam:JsonObject	  
	 */
public static JsonPath getJsonObject(Response response)
{
	return new JsonPath(response.asString());
}

/**
 * getTime
 * @return 
 */
public Date getCurrentTime() {

	Date d=null;
	try {
		//set End  time
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		d = new Date();
		String currenttime=format.format(d);
		System.out.println(format.parse(currenttime));
		d=format.parse(currenttime);
		
		
		
	}	catch(Exception e)	{
		
		System.out.println("Unable to get the Start-Time");
		logEvent("Fail", "Unable to get the Start-Time");
	}
	
	return d;
}



/**
 * Read Payloads
 * @return 
 * 
 */

public String parsePayloadJSONFile(String payloadFile)
{
	System.out.println("Parsing Payload Json File");
	JSONObject payLoadJsonobject = null;
	JSONParser Paylodparser = new JSONParser();
	try
	{
		Object object=parser.parse(new FileReader("./Payloads/"+payloadFile+".json"));
		payLoadJsonobject = (JSONObject)object;
		
		System.out.println("Payload Json is parsed sucessfully");
		
	}catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
	
	return payLoadJsonobject.toJSONString();
}

public JSONObject getPayloadJSONObject(String payloadFile)
{
	System.out.println("Parsing Payload Json File");
	JSONObject payLoadJsonobject = null;
	JSONParser Paylodparser = new JSONParser();
	try
	{
		Object object=parser.parse(new FileReader("./Payloads/"+payloadFile+".json"));
		payLoadJsonobject = (JSONObject)object;
		
		System.out.println("Payload Json is parsed sucessfully");
		
	}catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
	
	return payLoadJsonobject;
}



}
