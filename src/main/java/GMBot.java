import net.dv8tion.jda.api.JDABuilder;
import javax.security.auth.login.LoginException;

import io.github.cdimascio.dotenv.Dotenv;

public class GMBot {
    public static void main(String[] args) throws LoginException {
        Dotenv dotenv = Dotenv.load();
        String GM_APE_TOKEN = dotenv.get("GM_APE_TOKEN");

        JDABuilder jdaBuilder =
                JDABuilder.createDefault(GM_APE_TOKEN);

        jdaBuilder.build();
    }
}
