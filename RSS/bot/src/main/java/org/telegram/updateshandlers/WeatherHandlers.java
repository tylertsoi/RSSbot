package org.telegram.updateshandlers;

import org.telegram.BotConfig;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.updatesreceivers.BotSession;
    //**********By Tyler Tsoi************
public class WeatherHandlers extends TelegramLongPollingBot {
    private static final String LOGTAG = "WEATHERHANDLERS";
    //set default language as English
    public static String language = "English";
    //set default Weather/Information warning URL as English
    public static String warnUrl = "http://rss.weather.gov.hk/rss/WeatherWarningSummaryv2.xml";
    public static String weatherUrl = "http://rss.weather.gov.hk/rss/CurrentWeather.xml";
    //Create variable storing ChatId to store the Id of User
    public static String ChatId;
    @Override
    
    //Get the bot user name
    public String getBotUsername() {
            // TODO Auto-generated method stub
            return BotConfig.USERNAMEWEATHER;
    }

    //Get the bot token
    @Override
    public String getBotToken() {
            // TODO Auto-generated method stub
            return BotConfig.TOKENWEATHER;
    }
    
    //function use for Sending Message to User
    public void Message(String ChatId, String Text){
    SendMessage sendMessageRequest = new SendMessage();
	sendMessageRequest.setChatId(ChatId.toString());
	sendMessageRequest.setText(Text);
	try{
		sendMessage(sendMessageRequest);
		} catch (TelegramApiException e){}
    }
    
    //function run when update received, get the message from User
    @Override
    public void onUpdateReceived(Update update) {
            //check if the update has a message
    		
            if(update.hasMessage()){
            	
                    Message message = update.getMessage();
                    
                    switch(language){
                    case "English" : 
                    	warnUrl = "http://rss.weather.gov.hk/rss/WeatherWarningSummaryv2.xml";
                    	weatherUrl = "http://rss.weather.gov.hk/rss/CurrentWeather.xml";
                    break;
                    case "TradChinese" : 
                    	warnUrl = "http://rss.weather.gov.hk/rss/WeatherWarningSummaryv2_uc.xml";
                    	weatherUrl = "http://rss.weather.gov.hk/rss/CurrentWeather_uc.xml";
                    break;
                    case "SimplifiedChinese" : 
                    	warnUrl = "http://gbrss.weather.gov.hk/rss/WeatherWarningSummaryv2_uc.xml";
                    	weatherUrl = "http://gbrss.weather.gov.hk/rss/CurrentWeather_uc.xml";
                    break;
                    }
                    
                    //check if the message has text. it could also  contain for example a location ( message.hasLocation() )
                    if(message.hasText()){
                    		String Id = message.getChatId().toString();
                    		ChatId = Id;
                    		if(message.getText().contains("/topics")){
                    			String Text = "currentWeather" + "\n" + "currentWarning" + "\n" + "subscribeWeather" + "\n" + "subscribeWarning" + "\n" + "unSubscribeWeather" + "\n" + "unSubscribeWarning" + "\n" + "繁體中文" + "\n" + "简体中文" + "\n" + "English";
                    			Message(Id,Text);
                    		}
                    		
                    	    if (message.getText().contains("/currentWarning")){
                    	    	String Text = tryRSS.readRSSFeed(warnUrl,"Warning");
	                            Message(Id,Text);
                    	    } 
                    	    
                    	    if (message.getText().contains("/currentWeather")){
                    	    	String Text = tryRSS.readRSSFeed(weatherUrl,"Weather");
                    	    	Message(Id,Text);
                    	    }
                    	    if(message.getText().contains("/subscribeWeather")){
                    	    	BotSession.isSubscribedWeather = true;
                    	    	Message(Id,"Subscribed Weather");
                    	    }
                    	    if(message.getText().contains("/unSubscribeWeather")){
                    	    	BotSession.isSubscribedWeather = false;
                    	    	Message(Id,"Unsubscribed Weather");
                    	    }
                    	    if(message.getText().contains("/subscribeWarning")){
                    	    	BotSession.isSubscribedWarning = true;
                    	    	Message(Id,"Subscribed Warning");
                    	    }
                    	    if(message.getText().contains("/unSubscribeWarning")){
                    	    	BotSession.isSubscribedWarning = false;
                    	    	Message(Id,"Unsubscribed Warning");
                    	    }
                    	    if(message.getText().contains("/English")){
                    	    	language = "English";
                    	    	Message(Id,"OK!");
                    	    }
                    	    if(message.getText().contains("/繁體中文")){
                    	    	language = "TradChinese";
                    	    	Message(Id,"知道了");
                    	    }
                    	    if(message.getText().contains("/简体中文")) {
                    	    	language = "SimplifiedChinese";
                    	    	Message(Id,"Simplified Chinese!");
                    	    }
                    }//end if()
            }//end  if()

    }//end onUpdateReceived()
    
}