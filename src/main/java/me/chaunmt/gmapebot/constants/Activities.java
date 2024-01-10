package me.chaunmt.gmapebot.constants;

import me.chaunmt.gmapebot.utils.RandomGenerator;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Activity.ActivityType;

public class Activities {
    public static final String[] WATCHING = {
            "planet of apes",
            "tarzan",
            "apes fight",
            "life flows by"
    };

    public static final String[] PLAYING = {
            "jenshin",
            "with dj ape",
            "with banana",
            "with furiri"
    };

    // Listening to
    public static final String[] LISTENING = {
            "miss yun",
            "uwaauwaa"
    };

    // Competing in
    public static final String[] COMPETING = {
            "food fight"
    };

    public static Activity getRandomActivity() {
        ActivityType[] activities = ActivityType.values();
        ActivityType randomActivity = activities[RandomGenerator.getRandomInt(activities.length)];

        switch (randomActivity) {
            case WATCHING -> {
                return Activity.watching(WATCHING[RandomGenerator.getRandomInt(WATCHING.length)]);
            }

            case PLAYING -> {
                return Activity.watching(PLAYING[RandomGenerator.getRandomInt(PLAYING.length)]);
            }

            case LISTENING -> {
                return Activity.watching(LISTENING[RandomGenerator.getRandomInt(LISTENING.length)]);
            }

            case COMPETING -> {
                return Activity.watching(COMPETING[RandomGenerator.getRandomInt(COMPETING.length)]);
            }

            default -> {
                return Activity.watching(WATCHING[RandomGenerator.getRandomInt(WATCHING.length)]);
            }
        }
    }
}
