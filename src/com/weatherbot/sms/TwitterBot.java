package com.weatherbot.sms;

import twitter4j.Status;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterBot {
	
	Twitter twitter;
	
	TwitterBot()
	{
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("KXOZ5heEjNwc45BeacU3g")
		  .setOAuthConsumerSecret("A52Ns81Sq8xGEGwdu8GW9QHOeG5aTmsDcinayOKNQ")
		  .setOAuthAccessToken("505583219-FzJVzEqMxSvHHhmDRhSBShZQaO7lmz9FgLYqJEXy")
		  .setOAuthAccessTokenSecret("h8JV5zUmOH2T3PoF2Rosf5o4PGOywoKlWQXAlTbfs");
		TwitterFactory tf = new TwitterFactory(cb.build());
		twitter = tf.getInstance();
	}
	
	
	public String tweet(String text)
	{
		Status status;
		try {
			StatusUpdate s = new StatusUpdate(text);
			status = twitter.updateStatus(s);
			return status.getText();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Tweet not posted";
	}
}
