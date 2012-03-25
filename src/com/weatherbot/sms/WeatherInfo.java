package com.weatherbot.sms;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "weather")

public class WeatherInfo {
	
	private String location;
	private String temperature;
	private String humidity;
	private String datetime;
		
	public String toString()
	{
		
		return "Temperature : "+this.temperature+"`C | Humidity : "+this.humidity+"% | Location : "+this.location;
	}
	
	
	public WeatherInfo()
	{
			this.location= null;
			this.temperature = null;
			this.humidity = null;
			this.datetime = null;
			
		}
	public WeatherInfo(String location, String temperature, String humidity, String datetime)
	{
			this.location= location;
			this.temperature = temperature;
			this.humidity = humidity;
			this.datetime = datetime;
		}
		
		public String getLocation()
		{
			return location;
		}
		
		public void setLocation(String location)
		{
			this.location = location;
		}
		
		public String getTemperature()
		{
			return temperature;
		}
		
		public void setTemperature(String temperature)
		{
			this.temperature = temperature;
		}

		public String getHumidity()
		{
			return humidity;
		}
		
		public void setHumidity(String humidity)
		{
			this.humidity = humidity;
		}
		
		public String getDatetime() {
			return datetime;
		}


		public void setDatetime(String datetime) {
			this.datetime = datetime;
		}

}


