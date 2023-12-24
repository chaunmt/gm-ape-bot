import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.entities.Activity;

import java.util.*;

public class GMBot implements EventListener
{
    public static void main(String[] args)
            throws InterruptedException
    {
        Dotenv dotenv = Dotenv.load();                                  // Load API
        String GM_APE_TOKEN = dotenv.get("GM_APE_TOKEN");

        generateActivities();
        JDA jda = JDABuilder.createDefault(GM_APE_TOKEN)                // Create Bot
                .addEventListeners(new SlashCommandHandler())           // Slash Command
                .setActivity(randomActivity())
                .build();                                               // Build Bot

        jda.awaitReady();   // Wait until jda is ready
    }

    private static final ArrayList<Activity> activities = new ArrayList<>();

    private static void generateActivities() {
        activities.add(Activity.watching("Planet of Apes"));
        activities.add(Activity.playing("Jenshin"));
        activities.add(Activity.playing("HSR"));
        activities.add(Activity.playing("with DJ Ape"));
        activities.add(Activity.playing("with banana"));
        activities.add(Activity.playing("with banana"));
        activities.add(Activity.competing("with DJ Ape"));
    }

    private static Activity randomActivity()
    {
        Random rand = new Random();
        int length = activities.size();
        return activities.get(rand.nextInt(length));
    }
}
