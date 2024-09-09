package me.chaunmt.gmapebot.events;

import me.chaunmt.gmapebot.commands.*;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class SlashCommandInteractionListener extends ListenerAdapter
{
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event)
    {
        switch (event.getName()) {
            case "hello" -> HelloCommand.run(event);
            case "goodbye" -> GoodByeCommand.run(event);
            case "ping" -> PingCommand.run(event);
            case "game" ->GameCommand.run(event);
            case "valheim" -> ValheimCommand.run(event);
            case "greenhell" -> GreenHellCommand.run(event);
            case "dadjoke" -> DadJokeCommand.run(event);
            case "poll" -> PollCommand.run(event);
            case "donate" -> DonateCommand.run(event);
            default -> event.reply("Me don't know that").queue();
        }
    }
}