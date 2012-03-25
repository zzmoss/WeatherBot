package com.weatherbot.sms;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;

import de.progra.charting.*;
import de.progra.charting.model.*;
import de.progra.charting.render.*;

public class WeatherGraphing {

	ArrayList<WeatherInfo> winfo;
	double[] temp,humi;
	void fetchData()
	{
		PreparedQuery pq1 = Utils.getWeatherResultSet();
		winfo = new ArrayList<WeatherInfo>();
		int i=0;
		for (Entity result : pq1.asIterable()) {
			i+=1;
			//winfo.add( new WeatherInfo(result.getProperty("location").toString(),result.getProperty("temperature").toString(),result.getProperty("humidity").toString(),result.getProperty("date").toString()));
			temp[i]= Double.parseDouble(result.getProperty("temperature").toString());
		} 
		
	}
	public void drawGraph()
	{
System.out.println("** Creating Line Chart Test.");
        
		//fetchData();
		
        //int[] quadr = {0, 1, 4, 9, 16, 25, 36};
        double[] exp = {32, 31.76, 34.1, 38, 26, 32, 44.6};        
        double[] columns = {0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0};
        
        DefaultDataSet[] ds = new DefaultDataSet[1];
        ds[0] = new DefaultDataSet(ChartUtilities.transformArray(exp),
                                   ChartUtilities.transformArray(columns),
                                   CoordSystem.FIRST_YAXIS,
                                   "Temperature vs something");
       /*// ds[1] = new DefaultDataSet(ChartUtilities.transformArray(quadr),
                                   ChartUtilities.transformArray(columns),
                                   CoordSystem.FIRST_YAXIS,
                                   "Quadratic Growth");
        
      //  ds[2] = new DefaultDataSet(ChartUtilities.transformArray(exp),
                                   ChartUtilities.transformArray(columns),
                                   CoordSystem.FIRST_YAXIS,
                                   "Exponential Growth");
       */ 
        String title = "Growth Factor Comparison";
        
        int width = 640;
        int height = 480;
        
        DefaultChartDataModel data = new DefaultChartDataModel(ds);
        
        // Look at the next 3 lines:
        data.setAutoScale(true);
        DefaultChart c = new DefaultChart(data, title, DefaultChart.LINEAR_X_LINEAR_Y);
        c.addChartRenderer(new LineChartRenderer(c.getCoordSystem(),
                           data), 1);
        
        c.setBounds(new Rectangle(0, 0, width, height));
        
        
        try {
            ChartEncoder.createPNG(new FileOutputStream("c:/massline.png"), c);
        } catch(EncodingException e) {
            System.out.println("** Error creating the simpleline.png file, showing the line chart.");
            e.getCause().printStackTrace();
            return;
        } catch(Exception e) {
            System.out.println("** Error creating the simpleline.png file, showing the line chart.");
            e.printStackTrace();
            return;
        }
        System.out.println("successfull.");
	}
	
	public static void main(String[] args)
	{
		WeatherGraphing wg = new WeatherGraphing();
		wg.drawGraph();
	}
}


