package me.chaunmt.gmapebot.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class GreenHellCommand {
    public static void run(SlashCommandInteractionEvent event) {
        event.reply("Apes will go to green hell soon!").queue();
    }
}
