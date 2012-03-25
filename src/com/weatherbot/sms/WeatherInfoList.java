package com.weatherbot.sms;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name="weatherhistory")
public class WeatherInfoList {
	
	private ArrayList<WeatherInfo> winfo;
	
	public WeatherInfoList()
	{
		super();
	}
	
	public WeatherInfoList(ArrayList<WeatherInfo> winfo) {
		
		this.winfo = winfo;
	}

	
	@XmlElement(name="weather")
	public ArrayList<WeatherInfo> getWinfo() {
		return winfo;
	}

	public void setWinfo(ArrayList<WeatherInfo> winfo) {
		this.winfo = winfo;
	}
	

}
