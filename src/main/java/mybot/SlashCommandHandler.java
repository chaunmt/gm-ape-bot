package mybot;

import data.Games;
import data.Valheim;
import io.github.cdimascio.dotenv.Dotenv;
import mybot.exapi.DadJoke;
import mybot.helper.JSONHandler;
import mybot.helper.RandomGenerator;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.json.JSONArray;

import java.awt.*;
import java.io.*;
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
                                .addOption(OptionType.BOOLEAN, "random",
                                        "pick me a game to play", true),
                        Commands.slash("valheim", "apes in valhalla"),
                        Commands.slash("greenhell", "apes running from aneh pepo"),
                        Commands.slash("dadjoke", "give me a dad joke kek"),
                        Commands.slash("poll", "let ape help you make a poll")
                                .addOption(OptionType.STRING, "question",
                                        "give it a question", true)
                                .addOption(OptionType.STRING, "choices",
                                        "give it choices separated by a comma", false),
                        Commands.slash("donate", "buy me a coffee")
                                .addOption(OptionType.BOOLEAN, "katsu",
                                        "buy katsu a coffee", false)
                                .addOption(OptionType.BOOLEAN, "levan",
                                        "buy levan a coffee", false)
                )
                .queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event)
    {
        switch (event.getName()) {
            case "hello" ->
                    event.reply(getRandomString("data/emojis.json", new String[]{"fun"})).queue();
            case "goodbye" ->
                    event.reply(getRandomString("data/emojis.json", new String[]{"sad"})).queue();
            case "ping" ->
                    event.reply(event.getJDA().getGatewayPing() + " ms da").queue();
            case "game" ->
            {
                ArrayList<String> games = JSONHandler.getArr("data/games.json", new String[]{"games"});
                if (event.getOptionsByName("random").get(0).getAsBoolean())
                {
                    event.reply(games.get(RandomGenerator.getInt(games.size()))).queue();
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
//                    event.reply(getModString("data/valheim.json")).queue();
                    event.reply(Games.VALHEIM).queue();
            case "greenhell" ->
                    event.reply("Apes will go to green hell soon!").queue();
            case "dadjoke" ->
            {
                try
                {
                    event.reply(DadJoke.reader()).queue();
                    event.getHook().sendMessage(
                            getRandomString("data/emojis.json", new String[]{"fun"})
                    ).queue();
                }
                catch (IOException e)
                {
                    throw new RuntimeException(e);
                }

            }
            case "poll" ->
                    generatePoll(event);
            case "donate" ->
                    generateDonateMsg(event);
            default ->
                    event.reply("Me don't know that").queue();
        }
    }

    private void generatePoll(SlashCommandInteractionEvent event)
    {
//        event.deferReply().queue();

        String question = event.getOptionsByName("question").get(0).getAsString();
        String choices;
        if (event.getOptionsByName("choices").size() != 0)
            choices = event.getOptionsByName("choices").get(0).getAsString();
        else choices = "Yes, No";

        EmbedBuilder embedMsg = new EmbedBuilder();
        embedMsg.setTitle(question);
        embedMsg.setColor(Color.CYAN);
        embedMsg.setDescription("Let's vote");

        String[] choicesArr = choices.split(",");
        if (choicesArr.length == 0)
        {
            event.getHook().sendMessage("Give me choices").queue();
        } else
        if (choicesArr.length == 1)
        {
            event.getHook().sendMessage("Be more democracy, you sussy baka").queue();
        } else
        if (choicesArr.length <= 10)
        {
            String codeType = null;

            if (choicesArr.length == 2)
                codeType = "hand";
            else
                codeType = "number";

            ArrayList<String> voteReactions =
                    JSONHandler.getArr("data/emojis.json", new String[]{"code", codeType}, "discord");

            ArrayList<String> unicodeReactions =
                    JSONHandler.getArr("data/emojis.json", new String[]{"code", codeType}, "unicode");

            for (int i = 0; i < choicesArr.length; i++)
                embedMsg.addField(voteReactions.get(i) + " " + choicesArr[i], "\n", false);

            event.getHook().sendMessageEmbeds(embedMsg.build()).queue(msg ->
            {
                for (int i = 0; i < choicesArr.length; i++)
                    msg.addReaction(Emoji.fromUnicode(unicodeReactions.get(i))).queue();
            });
        } else
            event.getHook().sendMessage("That's too many choices, narrow it down").queue();
    }

    private void generateDonateMsg(SlashCommandInteractionEvent event)
    {
        StringBuilder donateMsg = new StringBuilder();
        if (event.getOptionsByName("katsu").size() == 0 && event.getOptionsByName("levan").size() == 0)
        {
            event.reply("https://www.buymeacoffee.com").queue();
        }
        else {
            if (event.getOptionsByName("katsu").size() > 0) {
                if (event.getOptionsByName("katsu").get(0).getAsBoolean())
                    donateMsg.append("https://www.buymeacoffee.com/kittokatsu\n");
                else
                    donateMsg.append("no coffee for katsu\n");
            }
            if (event.getOptionsByName("levan").size() > 0) {
                if (event.getOptionsByName("levan").get(0).getAsBoolean())
                    donateMsg.append("https://www.buymeacoffee.com/syukurm\n");
                else
                    donateMsg.append("no coffee for levan\n");
            }
            event.reply(String.valueOf(donateMsg)).queue();
        }
    }

    public String getRandomString(String link, String[] keys)
    {
        ArrayList<String> emoji = JSONHandler.getArr(link, keys);
        return emoji.get(RandomGenerator.getInt(emoji.size()));
    }

    private String getModString(String link)
    {
        return "Valheim IP: apegm.datho.st:21831\n"
                + "Password: Woow00kek\n"
                + "(Steam IP: apegm.datho.st:21832)\n\n"
                + "Required mods:\n"
                + getModString(link, new String[]{"mods", "required"})
                + "\nSuggested mods:\n"
                + getModString(link, new String[]{"mods", "suggested"})
                + "\nStopped working mods:\n"
                + getModString(link, new String[]{"mods", "stopped"});
    }

    private String getModString(String link, String[] keys)
    {
        JSONArray mods = JSONHandler.getJSONArr(link, keys);
        ArrayList<String> names = JSONHandler.getArr(mods, "name");
        ArrayList<String> authors = JSONHandler.getArr(mods, "author");
        ArrayList<String> urls = JSONHandler.getArr(mods, "url");
        ArrayList<String> notes = JSONHandler.getArr(mods, "note");

        StringBuilder allMods = new StringBuilder();
        for (int i = 0; i < names.size(); i++)
        {
            allMods.append(i).append(". ")
                    .append('[')
                    .append(names.get(i))
                    .append(" - ").append(authors.get(i))
                    .append(" (").append(notes.get(i)).append(")")
                    .append(']')
                    .append("(<").append(urls.get(i)).append(">)")
                    .append("\n")
            ;
        }

        return allMods.toString();
    }
}
