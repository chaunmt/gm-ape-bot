package me.chaunmt.gmapebot.commands;

import me.chaunmt.gmapebot.constants.Games;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class GameCommand {
    public static void run(SlashCommandInteractionEvent event) {
        if (event.getOptionsByName("random").get(0).getAsBoolean())
        {
            event.reply(Games.getRandomGame()).queue();
        }
        else
        {
            event.reply(String.join("\n", Games.ALL)).queue();
        }
    }
}
