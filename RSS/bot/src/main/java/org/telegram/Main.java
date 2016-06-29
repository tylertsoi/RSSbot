package org.telegram;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.updateshandlers.WeatherHandlers;

public class Main {
	
    public static void main(String[] args) {

	    TelegramBotsApi telegramBotsApi = new TelegramBotsApi();  

	    try {
	        telegramBotsApi.registerBot(new WeatherHandlers());
	        
	        
	        
	    } catch (TelegramApiException e) {
	        
	    }//end catch()
    }//end main()
}
