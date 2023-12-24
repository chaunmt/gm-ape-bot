import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;

import java.util.EventListener;

public class GMBot implements EventListener
{
    public static void main(String[] args)
            throws InterruptedException
    {
        Dotenv dotenv = Dotenv.load();                                  // Load API
        String GM_APE_TOKEN = dotenv.get("GM_APE_TOKEN");

        JDA jda = JDABuilder.createDefault(GM_APE_TOKEN)                // Create Bot
                .addEventListeners(new SlashCommandHandler())           // Slash Command
                .setActivity(Activity.playing(" with dj ape"))
                .build();                                               // Build Bot

//        jda.awaitReady();   // Wait until jda is ready
    }
}
