package mybot;

import mybot.helper.JSONHandler;
import mybot.helper.RandomGenerator;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.json.JSONArray;
import org.json.JSONObject;

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
        JSONObject obj = JSONHandler.getJSONObj("data/activities.json");
        addActivitiesWithType(obj, "watching");
        addActivitiesWithType(obj, "playing");
        addActivitiesWithType(obj, "listening");
        addActivitiesWithType(obj, "competing");
        addActivitiesWithType(obj, "streaming");
    }

    private static void addActivitiesWithType(JSONObject obj, String type)
    {
        JSONArray arr = null;
        arr = JSONHandler.getJSONArr(obj, new String[]{type});
        for (int i = 0; i < arr.length(); i++)
            switch (type) {
                case "watching" -> activities.add(Activity.watching(arr.get(i).toString()));
                case "playing" -> activities.add(Activity.playing(arr.get(i).toString()));
                case "listening" -> activities.add(Activity.listening(arr.get(i).toString()));
                case "competing" -> activities.add(Activity.competing(arr.get(i).toString()));
                case "streaming" -> activities.add(Activity.streaming(arr.get(i).toString(), ""));
            }
    }

    private static Activity randomActivity()
    { return activities.get(RandomGenerator.getInt(activities.size())); }
}
