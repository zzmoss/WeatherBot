package com.weatherbot.sms;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.SortDirection;

public class Utils {

	public static PreparedQuery getWeatherResultSet()
	{
		try {
			DatastoreService datastore1 = DatastoreServiceFactory.getDatastoreService();
			Query q1 = new Query("WeatherInfo");
			q1.addSort("date", SortDirection.DESCENDING);
			return datastore1.prepare(q1);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
}

