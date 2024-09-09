package me.chaunmt.gmapebot;

import io.github.cdimascio.dotenv.Dotenv;

public class Configs {

    public static final String BOT_TOKEN;
    public static final String THE_APES_GUILD_ID;

    static  {
        Dotenv dotenv = Dotenv.load();
        BOT_TOKEN = dotenv.get("BOT_TOKEN");
        THE_APES_GUILD_ID = dotenv.get("THE_APES_GUILD_ID");
    }
}
