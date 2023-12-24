import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.time.Instant;

import static java.lang.Math.abs;

public class SlashCommandHandler extends ListenerAdapter
{
    public void onReady(ReadyEvent event) {
        event.getJDA().updateCommands()
                .addCommands(
                        Commands.slash("ping", "Ping?")
                )
                .queue();
    }

    @Override
    public void onSlashCommandInteraction (SlashCommandInteractionEvent event)
    {
        switch (event.getName())
        {
            case "ping":
                long created = event.getTimeCreated().toInstant().toEpochMilli();
                long current = Instant.now().toEpochMilli();
                event.reply((abs(current - created) % 1000) + " ms").queue();
                break;
            default:
                event.reply("Me don't know that").queue();
        }
    }
}
