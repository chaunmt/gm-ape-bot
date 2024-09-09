package me.chaunmt.gmapebot.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class PingCommand {
    public static void run(SlashCommandInteractionEvent event) {
        event.reply(event.getJDA().getGatewayPing() + " ms da").queue();
    }
}
