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
        if (msg.toLowerCase().contains("@GM Ape is it true that"))
        {
            event.getChannel().sendMessage(RandomGenerator.getYesNo(100))
                    .setMessageReference(event.getMessageId()).queue();
        } else
        if (msg.contains("@GM Ape"))
        {
            event.getChannel().sendMessage("you call?").setMessageReference(event.getMessageId()).queue();
        }
    }
}
