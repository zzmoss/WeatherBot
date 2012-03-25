package com.weatherbot.sms;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException; 

import java.net.URL;
import java.io.InputStream;

public class WeatherGraph {

	 public String drawGraph(){
		    try {

		    		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		                DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

		            //Google chart url
		            URL url = new URL("http://weathersms2web.appspot.com/weather/api/history");
		            String chart= "http://chart.apis.google.com/chart?cht=lc&chs=500x300&&chtt=SSN%20Weather%20Report&chd=t:";


                            InputStream stream = url.openStream();

                            StringBuffer hum = new StringBuffer();
		            StringBuffer temp = new StringBuffer();
		            String humchart,tempchart;
		            hum.append(chart);
		            temp.append(chart);

		            Document doc = docBuilder.parse(stream);
		            // normalize text representation
		            doc.getDocumentElement ().normalize ();

		            NodeList listOfweather = doc.getElementsByTagName("weather");
		            int totalhumidity = listOfweather.getLength();

		            for(int s=0; s<listOfweather.getLength() ; s++){
		                Node humidityNode = listOfweather.item(s);
		                if(humidityNode.getNodeType() == Node.ELEMENT_NODE){

		                	Element humidityElement = (Element)humidityNode;
		                    //-------
		                    NodeList humidityList = humidityElement.getElementsByTagName("humidity");
		                    Element humidityElement1 = (Element)humidityList.item(0);
		                    NodeList textFNList = humidityElement1.getChildNodes();
		                    hum.append(((Node)textFNList.item(0)).getNodeValue().trim());
		                    hum.append(",");


		                    System.out.println("Humidity : " + 
		                           ((Node)textFNList.item(0)).getNodeValue().trim());

                                    //-------
		                    NodeList temperatureList = humidityElement.getElementsByTagName("temperature");
		                    Element temperatureElement = (Element)temperatureList.item(0);
		                    NodeList textLNList = temperatureElement.getChildNodes();

		                    temp.append(((Node)textLNList.item(0)).getNodeValue().trim());
		                    temp.append(",");
                                    System.out.println("Temperature : " + 
		                           ((Node)textLNList.item(0)).getNodeValue().trim());

                                    //----

		                }//end of if clause
		            }//end of for loop with s var

                            humchart = hum.substring(0,hum.length()-1); 
		            tempchart = temp.substring(0,temp.length()-1);

                            return humchart;
		            	

		        }catch (SAXParseException err) {
		        System.out.println ("** Parsing error" + ", line " 
		             + err.getLineNumber () + ", uri " + err.getSystemId ());
		        System.out.println(" " + err.getMessage ());
		        }catch (SAXException e) {
		        Exception x = e.getException ();
		        ((x == null) ? e : x).printStackTrace ();
		        }catch (Throwable t) {
		        t.printStackTrace ();
		        }
		        return "Error";
	 }
}