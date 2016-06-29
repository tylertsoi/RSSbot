# RSSbot
This is the project for Weather Information RSS feed (XML). The Weather Information is retreived from Hong Kong Gov Observatory.

User of bot may choose to subscribe weather/wanring information of Hong Kong of not. Language option is also provided (English/Traditional Chinese/Simplified Chinese).

This is a Java only Telegrambot project using Long Polling Method. GetUpdates() is used for check input message and Thread is used for checking subscribed updated information.

To use and edit this project, please down eclipse java EE. Instructions:

Create your own bot from BotFather.

Record your token number and bot name.

Import-> choose existing Maven project and choose file (RSS) and then change the token in BotConfig.java to your own token.

Right Click project file in project explorer, choose properties -> Java Compiler -> change the compiler compliance level to 1.8.

Run Main.java in eclipse.

type commands in your bot

My own works : tryRSS.java, weatherHandler.java, BotSession.java Other things are import from the Api given by telegramBotApi.

Feel free to contact me for any problem

Visit TelegramBot website: https://core.telegram.org/bots/samples

Used open source libraries on GitHub: https://github.com/rubenlagus/TelegramBots

Tyler
