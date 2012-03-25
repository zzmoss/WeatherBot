package com.weatherbot.sms;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;

@Path("api")
public class WeatherBotAPI {

	@GET
    @Produces( { MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Path("latest")
	public WeatherInfo getWeatherXml() 
    {
		PreparedQuery pq1 = Utils.getWeatherResultSet();
		for (Entity result : pq1.asIterable()) {
			return new WeatherInfo(result.getProperty("location").toString(),result.getProperty("temperature").toString(),result.getProperty("humidity").toString(),result.getProperty("date").toString());
		} 
		return null;
    }
	
	@GET
    @Produces( { MediaType.TEXT_XML})
    @Path("latest/location")
	public String getLocationXml() 
    {
		PreparedQuery pq1 = Utils.getWeatherResultSet();
		for (Entity result : pq1.asIterable()) {
			return "<location>"+result.getProperty("location").toString()+"</location>";
		} 
		return null;
    }
	@GET
    @Produces( { MediaType.TEXT_XML })
    @Path("latest/temperature")
	public String getTemperatureXml() 
    {
		PreparedQuery pq1 = Utils.getWeatherResultSet();
		for (Entity result : pq1.asIterable()) {
			return "<temperature>"+result.getProperty("temperature").toString()+"</temperature>";
		} 
		return null;
    }
	
	@GET
    @Produces( { MediaType.TEXT_XML })
    @Path("latest/humidity")
	public String getHumidityXml() 
    {
		PreparedQuery pq1 = Utils.getWeatherResultSet();
		for (Entity result : pq1.asIterable()) {
			return "<humidity>"+result.getProperty("humidity").toString()+"</humidity>";
		} 
		return null;
    }
	
	@GET
    @Produces( { MediaType.TEXT_XML })
    @Path("history")
    public WeatherInfoList getWeatherListXml() 
    {
		PreparedQuery pq1 = Utils.getWeatherResultSet();
		ArrayList<WeatherInfo> winfo = new ArrayList<WeatherInfo>();
		
		for (Entity result : pq1.asIterable()) {
			winfo.add( new WeatherInfo(result.getProperty("location").toString(),result.getProperty("temperature").toString(),result.getProperty("humidity").toString(),result.getProperty("date").toString()));
		} 
		return (new WeatherInfoList(winfo));
		 
    }
		
	
	
	
}
