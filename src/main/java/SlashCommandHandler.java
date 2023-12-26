import io.github.cdimascio.dotenv.Dotenv;
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
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class SlashCommandHandler extends ListenerAdapter
{
    public void onReady(ReadyEvent event)
    {
        Dotenv dotenv = Dotenv.load();                                  // Load API
        String THE_APES_GUILD_ID = dotenv.get("THE_APES_GUILD_ID");

//        // Delete old global commands
//        event.getJDA()
//                .updateCommands()
//                .queue();
//        // Delete old guild commands
//        Objects.requireNonNull(event.getJDA().getGuildById("884490415208804392"))
//                .updateCommands()
//                .queue();

        // Update guild commands
        Objects.requireNonNull(event.getJDA().getGuildById(THE_APES_GUILD_ID))
                .updateCommands()
                .addCommands(
                        Commands.slash("hello", "say hello to ape"),
                        Commands.slash("goodbye", "say goodbye to ape"),
                        Commands.slash("ping", "how is ape doing?"),
                        Commands.slash("game", "what games will ape play?")
                                .addOption(OptionType.BOOLEAN, "random", "pick me a game to play", true),
                        Commands.slash("valheim", "apes in valhalla"),
                        Commands.slash("greenhell", "apes running from aneh pepo"),
                        Commands.slash("dadjoke", "give me a dad joke kek")
                )
                .queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event)
    {
        switch (event.getName()) {
            case "hello" ->
                    event.reply(RandomEmoji(generateFunEmoji())).queue();
            case "goodbye" ->
                    event.reply(RandomEmoji(generateSadEmoji())).queue();
            case "ping" ->
                    event.reply(event.getJDA().getGatewayPing() + " ms da").queue();
            case "game" ->
            {
                ArrayList<String> games = generateGames();
                if (event.getOptionsByName("random").get(0).getAsBoolean())
                {
                    Random rand = new Random(System.currentTimeMillis());
                    event.reply(games.get(rand.nextInt(games.size()))).queue();
                }
                else
                {
                    StringBuilder rep = new StringBuilder();
                    for (int i = 0; i < games.size(); i++)
                        rep.append(i).append(". ")
                                .append(games.get(i)).append("\n");
                    event.reply(rep.toString()).queue();
                }
            }
            case "valheim" ->
            {
                JSONObject obj = getJsonData("data/valheim.json");

                String allMods =
                        "Required mods : \n" +
                        getModStringFromJson(obj, "required") +
                        "\nSuggested mods : \n" +
                        getModStringFromJson(obj, "suggested") +
                        "\nStopped working mods : \n" +
                        getModStringFromJson(obj, "stopped");

                event.reply(allMods).queue();
            }
            case "greenhell" ->
                    event.reply("Apes will go to green hell soon!").queue();
            case "dadjoke" ->
            {
                try
                {
                    event.reply(DadJokeReader()).queue();
                    event.getHook().sendMessage(RandomEmoji(generateFunEmoji())).queue();
                }
                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }

            }
            default ->
                    event.reply("Me don't know that").queue();
        }
    }

    private String DadJokeReader() throws IOException {
        URL dadjoke = new URL("https://icanhazdadjoke.com/");
        URLConnection dc = dadjoke.openConnection();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(dc.getInputStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            if (inputLine.contains("<meta name=\"twitter:description\" content=\""))
            {
                inputLine = inputLine.replace("<meta name=\"twitter:description\" content=\"", "");
                inputLine = inputLine.replace("\">", "");
                return (inputLine);
            }

        in.close();
        return "Me can't find any dad joke :(";
    }

    private String RandomEmoji(ArrayList<String> emoji)
    {
        Random rand = new Random(System.currentTimeMillis());
        return emoji.get(rand.nextInt(emoji.size()));
    }

    private ArrayList<String> generateFunEmoji()
    {
        ArrayList<String> funEmoji = new ArrayList<>();
        funEmoji.add("<:oneesama_bleh:1110748595294056479>");
        funEmoji.add("<:Neesama_uwaauwaa:1181005202686480484>");
        funEmoji.add("<:Venti_yey:1181009539009630331>");
        funEmoji.add("<:Kokomi_think:1181011128076214303>");
        funEmoji.add("<:Lynette_spark:1181006569903759431>");
        funEmoji.add("<:hutao_love:1007604545213444226>");
        funEmoji.add("<:hutao_wink:1007606429970071624>");
        funEmoji.add("<:hutao_greetings:1007813726298193930>");
        funEmoji.add("<:Faruzan_smort:1181010143735980136>");
        funEmoji.add("<:doggo_kek:1007615513217736775>");
        funEmoji.add("<:tanuki_rolling:1007556411909230612>");
        funEmoji.add("<:jean_praise:1028892495444185200>");
        funEmoji.add("<:ganyu_pray:1007813723194404924>");
        funEmoji.add("<:ganyu_smile:1007617852687253524>");
        return funEmoji;
    }

    private ArrayList<String> generateSadEmoji()
    {
        ArrayList<String> sadEmoji = new ArrayList<>();
        sadEmoji.add("<:amber_saveme:1007813718479994941>");
        sadEmoji.add("<:March_7th_cry:1181005498779181147>");
        sadEmoji.add("<:kazoo_cri:1103271610162622534>");
        sadEmoji.add("<:Alhai_ughh:1181008931829596171>");
        sadEmoji.add("<:beidou_bye:1007813720128364625>");
        sadEmoji.add("<:ganyu_stare:1007605047577804900>");
        sadEmoji.add("<:layla_cri:1075322926670295070>");
        sadEmoji.add("<:keqing_mad:1007813002805919765>");
        sadEmoji.add("<:klee_shame:1007650909406380052>");
        sadEmoji.add("<:pain:1007616053939028048>");
        sadEmoji.add("<:qiqi_deadinside:1007643996329345055>");
        sadEmoji.add("<:xiao_deadinside:1007609464758743204>");
        return sadEmoji;
    }

    private ArrayList<String> generateGames()
    {
        ArrayList<String> games = new ArrayList<>();
        games.add("Valheim");
        games.add("Green Hell");
        games.add("Sons of the Forest");
        games.add("Monster Hunter: World");
        games.add("Jenshin Impact");
        games.add("Honkai: Star Rail");
        return games;
    }
    private StringBuilder getModStringFromJson(JSONObject obj, String mod_type)
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

    private JSONObject getJsonData(String link)
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
