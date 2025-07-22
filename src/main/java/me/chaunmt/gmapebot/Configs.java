package me.chaunmt.gmapebot;

import io.github.cdimascio.dotenv.Dotenv;

public final class Configs {
    // Load from .env if present, otherwise just read from System.getenv()
    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();

    // Helper that pulls from DOTENV first, then System.getenv(), and throws if null/empty
    private static String require(String key) {
        String val = DOTENV.get(key, System.getenv("IMAGE_NAME"));
        if (val == null || val.isBlank()) {
            throw new IllegalStateException(
                    "Missing required environment variable: " + key
            );
        }
        return val;
    }

    // Reads from the .env or, if missing, from the real env
    public static final String IMAGE_NAME = require("IMAGE_NAME");
    public static final String BOT_TOKEN = require("BOT_TOKEN");
    public static final String THE_APES_GUILD_ID = require("THE_APES_GUILD_ID");

    private Configs() {
        // prevent instantiation
    }
}
