package me.chaunmt.gmapebot.events;

import me.chaunmt.gmapebot.Configs;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.Objects;

public class ReadyListener extends ListenerAdapter {
    public void onReady(ReadyEvent event) {

        // Update guild commands
        Objects.requireNonNull(event.getJDA().getGuildById(Configs.THE_APES_GUILD_ID))
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
                )
                .queue();
    }
}
