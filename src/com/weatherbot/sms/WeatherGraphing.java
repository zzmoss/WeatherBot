package com.weatherbot.sms;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException; 

//tsuji:note [a] adding these
import java.net.URL;
import java.io.InputStream;

public class WeatherGraphing {

	 public String drawGraph(){
		    try {
		            int s,r;
		    		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

		            //tsuji:note [b] change this
		            //Document doc = docBuilder.parse (new File("book.xml"));
		            URL url = new URL("http://weathersms2web.appspot.com/weather/api/history");
		            String chart= "http://chart.apis.google.com/chart?chs=540x320&cht=lxy&chco=3072F3,FF0000&chtt=SSN%20Weather%20Report&chdl=Humidity|Temperature&chdlp=b&chls=2,4,1|1&chma=5,5,5,25&chd=t:10,20,40,80,90,95,99|";
		            int len=chart.length();
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

		            for(s=0; s<listOfweather.getLength() ; s++){
		                Node humidityNode = listOfweather.item(s);
		                if(humidityNode.getNodeType() == Node.ELEMENT_NODE){

		                	Element humidityElement = (Element)humidityNode;
		                    //-------
		                    NodeList humidityList = humidityElement.getElementsByTagName("humidity");
		                    Element humidityElement1 = (Element)humidityList.item(0);
		                    NodeList textFNList = humidityElement1.getChildNodes();
		                    hum.append(((Node)textFNList.item(0)).getNodeValue().trim());
		                    hum.append(",");


		                    /*System.out.println("Humidity : " + 
		                           ((Node)textFNList.item(0)).getNodeValue().trim());*/
		                    //-------
		                }
		            }
		                    humchart = hum.substring(0,hum.length()-1);
		                    temp.append(humchart);
		                    temp.append("|-1|");

		                    for(r=0; r<listOfweather.getLength() ; r++){
		                    Node humidityNode = listOfweather.item(r);
		                    Element humidityElement = (Element)humidityNode;
				            if(humidityNode.getNodeType() == Node.ELEMENT_NODE){
		                    NodeList temperatureList = humidityElement.getElementsByTagName("temperature");
		                    Element temperatureElement = (Element)temperatureList.item(0);
		                    NodeList textLNList = temperatureElement.getChildNodes();
		                    /*System.out.println("Temperature : " + 
		                           ((Node)textLNList.item(0)).getNodeValue().trim());*/
		                    temp.append(((Node)textLNList.item(0)).getNodeValue().trim());
		                    temp.append(",");
		                    //----

		                }//end of if clause
		            }//end of for loop with s var

		            tempchart = temp.substring(0,temp.length()-1);
		            //System.out.println("Humidity chart link : "+humchart);
		            System.out.println("Chart link : "+tempchart);
		            return tempchart;

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
		    	return null;

	 }
}


