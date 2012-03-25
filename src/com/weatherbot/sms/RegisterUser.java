package com.weatherbot.sms;



import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class RegisterUser {
	
	
	public String handleRegistration(String twMessage, String twMobileId) {
		
		 DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		    
	     String[] msgTokens = twMessage.split(" ");
	     if (msgTokens.length < 2) {
	         return "Invalid Inputs. Usage is  '@weatherbotssn register userName'";
	     }

	      String userName = msgTokens[1].trim();
	      Query q = new Query("Users");
	      q.addFilter("usr", Query.FilterOperator.EQUAL, userName);
	      PreparedQuery pq = datastore.prepare(q);
	      String username = null;
	      for (Entity result : pq.asIterable()) {
	        username = (String) result.getProperty("usr");
	        
	      }
	      if (username != null) {

	         return "A user by username "
	               + userName
	               + " already exists. Pick another user name and try again. Sorry :(";
	      }
	      else{
	      DatastoreService datastore1 = DatastoreServiceFactory.getDatastoreService();
	      Entity user = new Entity("Users");
			 
	      user.setProperty("mobileid", twMobileId);
	      user.setProperty("usr", userName);
	      
	      datastore1.put(user);
	      }
	      return "Successfully registered. Your user name is " + userName;

	   }
}
