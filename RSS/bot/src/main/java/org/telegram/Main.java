package org.telegram;

import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.updateshandlers.WeatherHandlers;


/**
 * @author Ruben Bermudez
 * @version 1.0
 * @brief Main class to create all bots
 * @date 20 of June of 2015
 */
public class Main {
	
    public static void main(String[] args) {

	    TelegramBotsApi telegramBotsApi = new TelegramBotsApi();  

	    try {
	        telegramBotsApi.registerBot(new WeatherHandlers());
	        
	        
	        
	    } catch (TelegramApiException e) {
	        
	    }//end catch()
    }//end main()
}
