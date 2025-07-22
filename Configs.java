package me.chaunmt.gmapebot;

import io.github.cdimascio.dotenv.Dotenv;

public final class Configs {
    // Load from .env if present, otherwise just read from System.getenv()
    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMalformed()
            .ignoreIfMissing()
            .load();

    // Reads from the .env or, if missing, from the real env
    public static final String DISCORD_TOKEN =
            dotenv.get("IMAGE_NAME", System.getenv("IMAGE_NAME"));
    public static final String DISCORD_TOKEN =
            dotenv.get("BOT_TOKEN", System.getenv("BOT_TOKEN"));
    public static final String DISCORD_TOKEN =
            dotenv.get("THE_APES_GUILD_ID", System.getenv("THE_APES_GUILD_ID"));

    private Configs() {
        // prevent instantiation
    }
}
