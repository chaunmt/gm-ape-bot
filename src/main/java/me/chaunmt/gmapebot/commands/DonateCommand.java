package me.chaunmt.gmapebot.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class DonateCommand {
    public static void run(SlashCommandInteractionEvent event) {
        event.reply("https://www.buymeacoffee.com/kittokatsu\nhttps://www.buymeacoffee.com/syukurm").queue();
    }
}
