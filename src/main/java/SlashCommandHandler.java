import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;


public class SlashCommandHandler extends ListenerAdapter
{
    public static void main(String[] args)
    {
        Dotenv dotenv = Dotenv.load();                                  // Load API
        String GM_APE_TOKEN = dotenv.get("GM_APE_TOKEN");

        JDA jda = JDABuilder.createDefault(GM_APE_TOKEN)                // Create Bot
                .addEventListeners(new SlashCommandHandler())           // Slash Command
                .build();

        jda.updateCommands()
                .addCommands(
                        Commands.slash("ping", "Ping?"),
                        Commands.slash("pong", "Pong?")
                )
                .queue();
    }

//    public void onReady(ReadyEvent event) {
//        event.getJDA().updateCommands()
//                .addCommands(
//                        Commands.slash("ping", "Ping?"),
//                        Commands.slash("pong", "Pong?")
//                )
//                .queue();
//    }
//    @Override
    public void onSlashCommand (SlashCommandInteractionEvent event)
    {
        switch (event.getName())
        {
            case "ping"
                -> event.reply("pong").queue();
            case "pong"
                -> event.reply("ping").queue();
            default
                -> event.reply("wat?").queue();
        }
    }
//    @Override
//    public void onMessageReceived(MessageReceivedEvent event)
//    {
//        if (event.getAuthor().isBot()) return;
//        // We don't want to respond to other bot accounts, including ourself
//        Message message = event.getMessage();
//        String content = message.getContentRaw();
//        // getContentRaw() is an atomic getter
//        // getContentDisplay() is a lazy getter which modifies the content for e.g. console view (strip discord formatting)
//        if (content.equals("!ping"))
//        {
//            MessageChannel channel = event.getChannel();
//            channel.sendMessage("Pong!").queue(); // Important to call .queue() on the RestAction returned by sendMessage(...)
//        }
//    }
}
