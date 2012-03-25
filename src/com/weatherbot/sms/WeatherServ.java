package com.weatherbot.sms;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


@Path("ws")
public class WeatherServ {
    
	public WeatherServ() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Retrieves representation of an instance of WeatherServ
     * @return an instance of String
    */
	
    		
    @GET
    @Produces( { MediaType.TEXT_HTML })
    @Path("receive")
    public String getWeather(@QueryParam("txtweb-message") String smstext, @QueryParam("txtweb-mobile") String mobile) 
    {
    
    	SmsParser p = new SmsParser();
    	TwitterBot twbot = new TwitterBot();
    	
    	WeatherInfo wobj = p.parse(smstext);
    	return "<html><head><style type='text/css'> body{text-align:center;} p{font-size:500%}    </style><meta name=\"txtweb-appkey\" content=\"ceafbac6-225d-4ca3-b194-0162ff033da3\" /></head><body><p>"
    +twbot.tweet(wobj.toString())+"Thanks!!!! , SSN College"+"</p></body></html>";

    }
    
    @GET
    @Produces( { MediaType.TEXT_HTML })
    @Path("regup")
    public String sendUpdates(@QueryParam("txtweb-message") String smstext, @QueryParam("txtweb-mobile") String mobile) 
    {
    	String[] msgTokens = smstext.split(" ");
	     if (msgTokens.length < 2) {
	    	 UpdatesHandler up = new UpdatesHandler();
	     	String response = up.handleUpdates(smstext, mobile);
	     	return "<html><head><style type='text/css'> body{text-align:center;} p{font-size:500%}    </style><meta name=\"txtweb-appkey\" content=\"cf3c25e0-e611-4bfb-baaf-691be80a2518\" /></head><body><p>"
	     +response+"</p></body></html>";
	         
	     }
	     else if(msgTokens[0].equalsIgnoreCase("register"))
	     {
	    	 RegisterUser reg = new RegisterUser();
	     	String response = reg.handleRegistration(smstext, mobile);
	     	return "<html><head><style type='text/css'> body{text-align:center;} p{font-size:500%}    </style><meta name=\"txtweb-appkey\" content=\"cf3c25e0-e611-4bfb-baaf-691be80a2518\" /></head><body><p>"
	     +response+"</p></body></html>";
	     }
	     return "<html><head><style type='text/css'> body{text-align:center;} p{font-size:500%}    </style><meta name=\"txtweb-appkey\" content=\"cf3c25e0-e611-4bfb-baaf-691be80a2518\" /></head><body><p>"
	     +smstext+" "+msgTokens.length+" Invalid Inputs. Usage is  '@weatherbotssn register userName' to register for our daily updates or '@weatherbotssn now' to receive current weather update"+"</p></body></html>";
	     
    }
    
    @GET
    
    @Path("graph")
    public String plotGraph()
    {
    	WeatherGraph wg = new WeatherGraph();
    	return wg.drawGraph();
    }
	
}

