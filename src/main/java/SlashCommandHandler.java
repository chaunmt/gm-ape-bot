import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.io.FileReader;

public class SlashCommandHandler extends ListenerAdapter
{
    public void onReady(ReadyEvent event)
    {
        event.getJDA().updateCommands()
                .addCommands(
                        Commands.slash("ping", "how is ape doing?"),
//                        Commands.slash("game", "what games will ape play?")
//                                .addOption(OptionType.BOOLEAN, "random", "pick me a game to play"),
//                        Commands.slash("test", "test"),
                        Commands.slash("valheim", "apes in valhalla"),
                        Commands.slash("greenhell", "apes running from aneh pepo")
                )
                .queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event)
    {
        switch (event.getName()) {
            case "ping" ->
                    event.reply(event.getJDA().getGatewayPing() + " ms da").queue();
            case "game" ->
            {
//                System.out.println(event.getOptionsByName("random").get(0));
                event.reply("Game");
            }
            case "valheim" ->
            {
                JSONObject obj = getJsonData("data/valheim.json");

                StringBuilder allMods = new StringBuilder();

                allMods.append("Required mods : \n");
                allMods.append(getStringFromJson(obj, "required"));
                allMods.append("\nSuggested mods : \n");
                allMods.append(getStringFromJson(obj, "suggested"));
                allMods.append("\nStopped working mods : \n");
                allMods.append(getStringFromJson(obj, "stopped"));

                event.reply(allMods.toString()).queue();
            }
            case "greenhell" ->
                    event.reply("Apes will go to green hell soon!").queue();
            default ->
                    event.reply("Me don't know that").queue();
        }
    }

    private static StringBuilder getStringFromJson(JSONObject obj, String mod_type)
    {
        JSONArray mods = (obj.getJSONObject("mods")).getJSONArray(mod_type);

        StringBuilder allMods = new StringBuilder();
        for (int i = 0; i < mods.length(); i++)
        {
            String modName = (String) mods.getJSONObject(i).get("name");
            String modAuthor = (String) mods.getJSONObject(i).get("author");
            String modLink = (String) mods.getJSONObject(i).get("url");
            allMods
                    .append(i).append(". ")
                    .append('[')
                        .append(modName)
                        .append(" - ").append(modAuthor)
                    .append(']')
                    .append("(<").append(modLink).append(">)")
                    .append("\n")
            ;
        }

        return allMods;
    }

    private static JSONObject getJsonData(String link)
    {
        try
        {
            JSONParser parser = new JSONParser();
            String str = parser.parse(new FileReader(link)).toString();
            return new JSONObject(str);
        }
        catch (IOException | org.json.simple.parser.ParseException e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
