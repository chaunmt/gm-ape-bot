package mybot.exapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class DadJoke
{
    public static String reader() throws IOException
    {
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
}
