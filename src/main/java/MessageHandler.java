import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Random;

public class MessageHandler extends ListenerAdapter
{
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String msg = event.getMessage().getContentDisplay();
        if (msg.contains("@GM Ape"))
        {
            event.getChannel().sendMessage("you call?").setMessageReference(event.getMessageId()).queue();
        }
        if (msg.toLowerCase().contains("is it true that"))
        {
            event.getChannel().sendMessage(RandomYesNo()).setMessageReference(event.getMessageId()).queue();
        }
    }

    private String RandomYesNo()
    {
        Random rand = new Random(System.currentTimeMillis());
        if (rand.nextInt(100) % 2 == 0) return "Yes";
        else return "No";
    }
}
