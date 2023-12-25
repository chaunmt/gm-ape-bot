import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class SlashCommandHandler extends ListenerAdapter
{
    public void onReady(ReadyEvent event)
    {
        event.getJDA().updateCommands()
                .addCommands(
                        Commands.slash("ping", "How is ape doing?")
                )
                .queue();
    }

    @Override
    public void onSlashCommandInteraction (SlashCommandInteractionEvent event)
    {
        switch (event.getName()) {
            case "ping" ->
                    event.reply(event.getJDA().getGatewayPing() + " ms da").queue();
            default ->
                    event.reply("Me don't know that").queue();
        }
    }
}
