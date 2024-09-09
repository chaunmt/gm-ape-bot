package me.chaunmt.gmapebot.commands;

import me.chaunmt.gmapebot.constants.Emojis;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class HelloCommand {
    public static void run(SlashCommandInteractionEvent event) {
        event.reply(Emojis.getRandomFunEmoji()).queue();
    }
}
