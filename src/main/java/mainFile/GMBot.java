package mainFile;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

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
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new MessageHandler())
                .setActivity(randomActivity())
                .build();                                               // Build Bot

        // Re-set bot activity every hour
        new Timer().schedule(new TimerTask(){
            public void run(){
                jda.getPresence().setActivity(randomActivity());
            }},0,3600_000);

        jda.awaitReady();   // Wait until jda is ready
    }

    private static final ArrayList<Activity> activities = new ArrayList<>();

    private static void generateActivities() {
        // watching
        activities.add(Activity.watching("planet of apes"));
        activities.add(Activity.watching("tarzan"));
        activities.add(Activity.watching("apes fight"));
        activities.add(Activity.watching("lady furiri"));
        activities.add(Activity.watching("life flows by"));
        // playing
        activities.add(Activity.playing("jenshin"));
        activities.add(Activity.playing("with dj ape"));
        activities.add(Activity.playing("with banana"));
        // listening to
        activities.add(Activity.listening("miss yun"));
        activities.add(Activity.listening("uwaauwaa"));
        // competing in
        activities.add(Activity.competing("food fight"));
    }

    private static Activity randomActivity()
    {
        Random rand = new Random(System.currentTimeMillis());
        return activities.get(rand.nextInt(activities.size()));
    }
}
