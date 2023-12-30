package mybot;

import mybot.helper.RandomGenerator;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageHandler extends ListenerAdapter
{
    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        String msg = event.getMessage().getContentDisplay();
        if (msg.contains("@GM Ape"))
        {
            if (msg.toLowerCase().replaceAll("\\s+","").contains("@gmapeisittruethat"))
            {
                event.getChannel().sendMessage(RandomGenerator.getYesNo(100))
                        .setMessageReference(event.getMessageId()).queue();
            } else
                event.getChannel().sendMessage(
                        RandomGenerator.getString("data/replies.json", new String[]{"mention"}))
                        .setMessageReference(event.getMessageId()).queue();
        }
    }
}
