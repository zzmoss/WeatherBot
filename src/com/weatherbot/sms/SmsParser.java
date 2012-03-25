package com.weatherbot.sms;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;


public class SmsParser {

	//private string smsText="";
	
	public WeatherInfo parse(String smsText)
	{
		//smsText="reporting weather from : [SSN] Temperature : [31.5]  Humidity : [60.9]";

	    String re1=".*?";	// Non-greedy match on filler
	    String re2="([+-]?\\d*\\.\\d+)(?![-+0-9\\.])";	// Square Braces 1
	    String sbraces1=null;
	    String info[]= new String[3];
	    int i = 1;
	    Pattern p = Pattern.compile(re1+re2,Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
	    Matcher m = p.matcher(smsText);
	    while (m.find())
	    {
	        sbraces1=m.group(1);
	        info[i++] = sbraces1.toString();//.replace("[", "").replace("]", "");
	    }
	    
	    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	    
	    Entity weatherinfo = new Entity("WeatherInfo");
	    
	    Date timestamp = new Date();
	    
	    if(info[1]!=null&&info[2]!=null)
	    {
	    	weatherinfo.setProperty("location", "SSN");
	    	weatherinfo.setProperty("temperature", info[2]);
	    	weatherinfo.setProperty("humidity", info[1]);
	    	weatherinfo.setProperty("date", timestamp);
	    	weatherinfo.setProperty("smstext",smsText);
	    	datastore.put(weatherinfo);
	    }
	    
	    
	    return new WeatherInfo("SSN",info[1],info[2],timestamp.toString());
	    
	}
}

