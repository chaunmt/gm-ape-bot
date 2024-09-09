package me.chaunmt.gmapebot.events;

import me.chaunmt.gmapebot.constants.Replies;
import me.chaunmt.gmapebot.utils.RandomGenerator;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageReceivedListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        String botId = event.getJDA().getSelfUser().getId();

        if (message.contains("<@" + botId + ">")) {
            if (message.toLowerCase().replaceAll("\\s+", "").contains("isittruethat")) {
                final String yesOrNo = RandomGenerator.getRandomBoolean() ? "Yes" : "No";
                event.getChannel().sendMessage(yesOrNo).setMessageReference(event.getMessageId()).queue();
            } else {
                event.getChannel().sendMessage(Replies.getRandomReply()).setMessageReference(event.getMessageId()).queue();
            }
        }
    }
}