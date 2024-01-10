package me.chaunmt.gmapebot;

import me.chaunmt.gmapebot.constants.Activities;
import me.chaunmt.gmapebot.events.MessageReceivedListener;
import me.chaunmt.gmapebot.events.ReadyListener;
import me.chaunmt.gmapebot.events.SlashCommandInteractionListener;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.EnumSet;
import java.util.Timer;
import java.util.TimerTask;

public class Bot {
    public static void main(String[] args) throws InterruptedException {
        EnumSet<GatewayIntent> intents = EnumSet.of(
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.MESSAGE_CONTENT
        );

        JDA jda = JDABuilder.createLight(Configs.BOT_TOKEN, intents)
                .addEventListeners(new ReadyListener(), new SlashCommandInteractionListener(), new MessageReceivedListener())
                .setActivity(Activities.getRandomActivity())
                .build();

        final int ONE_HOUR_IN_MILLISECONDS = 3_600_000;

        new Timer().schedule(new TimerTask() {
            public void run() {
                jda.getPresence().setActivity(Activities.getRandomActivity());
            }
        }, 0, ONE_HOUR_IN_MILLISECONDS);

        jda.awaitReady();
    }
}