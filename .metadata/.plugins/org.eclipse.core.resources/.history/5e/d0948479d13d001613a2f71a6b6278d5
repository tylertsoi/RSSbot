package de.vogella.rss.write;

import de.vogella.rss.model.Feed;
import de.vogella.rss.model.FeedMessage;
import de.vogella.rss.read.RSSFeedParser;

public class ReadTest {
	  public static void main(String[] args) {
		  
		    //RSSFeedParser parser = new RSSFeedParser("http://rss.weather.gov.hk/rss/CurrentWeather.xml");
				   
		    RSSFeedParser parser = new RSSFeedParser("http://rss.weather.gov.hk/rss/WeatherWarningSummaryv2.xml");
		    Feed feed = parser.readFeed();
		    System.out.println(feed);
		    for (FeedMessage message : feed.getMessages()) {
		      System.out.println(message);

		    }
		    
//		    RSSFeedParser parser2 = new RSSFeedParser("http://rss.weather.gov.hk/rss/LocalWeatherForecast_uc.xml");
//		    Feed feed2 = parser.readFeed();
//		    System.out.println(feed);
//		    for (FeedMessage message : feed.getMessages()) {
//		      System.out.println(message);
//
//		    }

		  }
}
