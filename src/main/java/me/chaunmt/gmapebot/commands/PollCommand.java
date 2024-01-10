package me.chaunmt.gmapebot.commands;

import me.chaunmt.gmapebot.constants.Emojis;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.awt.*;


public class PollCommand {
    public static void run(SlashCommandInteractionEvent event) {
        event.deferReply().queue();

        String question = event.getOptionsByName("question").get(0).getAsString();
        String choices;
        if (!event.getOptionsByName("choices").isEmpty())
            choices = event.getOptionsByName("choices").get(0).getAsString();
        else choices = "Yes, No";

        EmbedBuilder embedMsg = new EmbedBuilder();
        embedMsg.setTitle(question);
        embedMsg.setColor(Color.CYAN);
        embedMsg.setDescription("Let's vote");

        String[] choicesArr = choices.split(",");
        if (choicesArr.length == 0) {
            event.getHook().sendMessage("Give me choices").queue();
        } else if (choicesArr.length == 1) {
            event.getHook().sendMessage("Be more democracy, you sussy baka").queue();
        } else if (choicesArr.length == 2) {

            for (int i = 0; i < choicesArr.length; i++)
                embedMsg.addField(Emojis.CODE_HAND[i][0] + " " + choicesArr[i], "\n", false);

            event.getHook().sendMessageEmbeds(embedMsg.build()).queue(msg ->
            {
                for (int i = 0; i < choicesArr.length; i++)
                    msg.addReaction(Emoji.fromUnicode(Emojis.CODE_HAND[i][1])).queue();
            });
        } else if (choicesArr.length <= 10) {
            for (int i = 0; i < choicesArr.length; i++)
                embedMsg.addField(Emojis.CODE_NUMBER[i][0] + " " + choicesArr[i], "\n", false);

            event.getHook().sendMessageEmbeds(embedMsg.build()).queue(msg ->
            {
                for (int i = 0; i < choicesArr.length; i++)
                    msg.addReaction(Emoji.fromUnicode(Emojis.CODE_NUMBER[i][1])).queue();
            });
        } else
            event.getHook().sendMessage("That's too many choices, narrow it down").queue();
    }
}
