import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import javax.security.auth.login.LoginException;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.entities.Activity;

public class GMBot {
    public static void main(String[] args) throws LoginException {
        Dotenv dotenv = Dotenv.load();                                      // Load API
        String GM_APE_TOKEN = dotenv.get("GM_APE_TOKEN");

        JDABuilder jdaBuilder = JDABuilder.createDefault(GM_APE_TOKEN);     // Create Bot

        jdaBuilder.addEventListeners(new SlashCommandHandler());            // Slash Command

        jdaBuilder.setActivity(Activity.playing(" with banana"));
        JDA gmape = jdaBuilder.build();                                         // Build Bot
        gmape.addEventListener(new SlashCommandHandler());
    }
}
