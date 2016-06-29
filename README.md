# RSSbot
This is the project for Weather Information RSS feed (XML). The Weather Information is retreived from Hong Kong Gov Observatory.

User of bot may choose to subscribe weather/wanring information of Hong Kong of not. Language option is also provided (English/Traditional Chinese/Simplified Chinese).

This is a Java only Telegrambot project using Long Polling Method. GetUpdates() is used for check input message and Thread is used for checking subscribed updated information.

To use and edit this project, please down eclipse java EE. Instructions:

1. Create your own bot from BotFather.

2. Record your token number and bot name.

3. Import-> choose existing Maven project and choose file (RSS) and then change the token in BotConfig.java to your own token.
BotConfig.java and be found in src/main/java/org/telegram/BotConfig.java

4. Right Click project file in project explorer, choose properties -> Java Compiler -> change the compiler compliance level to 1.8.

5. Run Main.java in eclipse.

6.type commands in your bot
Available commands are as follows:

/topics - list all the command

/currentWeather - fetch current weather information

/currentWarning - fetch current weather warning

/subscribeWeather - subscribe the weather information

/subscribeWarning - subscribe the weather warning

/unSubscribeWeather - unsubscribe the weather information

/unSubscribeWarning - unsubscribe the weather warning

/繁體中文 - change the language of weather information/warning to Traditional Chinese

/简体中文 - change the language of weather information/warning to Simplified Chinese

/English - change the language of weather information/warning to English ( default setting )


My own works : tryRSS.java, weatherHandler.java, BotSession.java Other things are import from the Api given by telegramBotApi.

Feel free to contact me for any problem

Visit TelegramBot website: https://core.telegram.org/bots/samples

Used open source libraries on GitHub: https://github.com/rubenlagus/TelegramBots

Tyler
