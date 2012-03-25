package com.weatherbot.sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class UpdatesHandler {

	public String handleRegisteredUpdates() {

		String resultstring = null;
		try {
			
			PreparedQuery pq1 = Utils.getWeatherResultSet();

			for (Entity result : pq1.asIterable()) {
				resultstring = "<br>Location: "+(String) result.getProperty("location")+" <br>Temperature: "+(String) result.getProperty("temperature") + "`C <br>Humidity: "+(String) result.getProperty("humidity")+"%";
				break;
			} 
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query("Users");
		PreparedQuery pq = datastore.prepare(q);
		String mobileid = null, username = null, retresponse = "No response at handleupdates";
		for (Entity result : pq.asIterable()) {
			username = (String) result.getProperty("usr");
			mobileid = (String) result.getProperty("mobileid");
			
			String result1 = "Hey "+username+", Weather Update : "+resultstring;
			retresponse+= txtWebPush(result1, username, mobileid);
		}

		return retresponse;

	}

	public String txtWebPush(String resultstring, String username, String mobileid) 
	{
		try
		{
			String url = "http://api.txtweb.com/v1/push";
			
			//Encode all the parameters to UTF-8 format
			String data = "";
			data += URLEncoder.encode("txtweb-mobile", "UTF-8") + "=" + URLEncoder.encode(mobileid, "UTF-8");
			data += "&";
			data += URLEncoder.encode("txtweb-message", "UTF-8") + "=" + URLEncoder.encode("<HTML><head><meta name=\"txtweb-appkey\" content=\"cf3c25e0-e611-4bfb-baaf-691be80a2518\"></head><BODY>"+resultstring+"</BODY></HTML>", "UTF-8");
			data += "&";
			//You can find your PUBLISHER_KEY on the BUILD AND MANAGE MY APPS page on txtweb.com
			data += URLEncoder.encode("txtweb-pubkey", "UTF-8") + "=" + URLEncoder.encode("2401cf75-7f6f-4fdd-b1b8-56e97df9a222", "UTF-8");
			
			//Open an HTTP connection to the txtWeb PUSH API
			URLConnection conn = new URL(url).openConnection();
			
			//Ready to post the parameters to the API
			
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(data);
			wr.flush();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line,httpresult = null;
			while((line = br.readLine())!=null){
				httpresult+=line;
			}
			br.close();
			return httpresult;
			
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		return "No response";
	}

	public String handleUpdates(String twMessage, String twMobileId) {
		try 
		{
			PreparedQuery pq1 = Utils.getWeatherResultSet();
			String resultstring = null;
			for (Entity result : pq1.asIterable()) {
				resultstring = "<br>Location: "+(String) result.getProperty("location")+" <br>Temperature: "+(String) result.getProperty("temperature") + "`C <br>Humidity: "+(String) result.getProperty("humidity")+"%";
				break;
			} 
			return resultstring;

		} catch (Exception e) {
			return "Something's wrong!";
		}

	}
}
