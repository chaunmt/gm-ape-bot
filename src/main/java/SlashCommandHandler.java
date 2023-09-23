import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SlashCommandHandler extends ListenerAdapter {
    public void onSlashCommand (@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (command.equals("ping")) {
            event.reply("pong").queue();    // Put to queue --> Beg Discord to run
            System.out.println("pong pong");
        }
    }

    public void onReady(@NotNull ReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("ping", "Is bot online?"));
        event.getJDA().updateCommands().addCommands(commandData).queue();
    }
}
