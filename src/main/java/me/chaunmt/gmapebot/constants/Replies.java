package me.chaunmt.gmapebot.constants;

import me.chaunmt.gmapebot.utils.RandomGenerator;

public class Replies {
    public static final String[] MENTION =
            {
                    "you call?",
                    "welp",
                    "nope",
                    "hai",
                    ":0",
                    "uwaa uwaa",
                    "UWAA UWAA",
                    "bye",
                    "ello",
                    "yes mama",
                    "yes papa",
                    "wat",
                    "wat sup",
                    "i don't know anything",
                    "911 - What is your emergency?",
                    "Message failed. User is offline.",
                    "We're sorry you have reached a number that has been disconnected or is no longer in service.",
                    "We're sorry you have reached the maximum allowed messages for today.",
                    "ERR_CONNECTION_TIMED_OUT",
                    "The recipient you are sending to has chosen not to receive messages.",
                    "The message could not be delivered due to a temporary network error. Please try again later.",
                    "The person you are calling is deceased and their death is currently part of an ongoing investigation. Please stay on the line as we may need to jot down your personal information.",
                    "I would like to talk to you about your car's extended warranty.",
                    "You’ve reached your local morgue, you slice ‘em, we dice ‘em. How can we help you?",
                    "I would like to ask for your support <:pain:1007616053939028048>\nhttps://www.buymeacoffee.com/",
                    "Your account has been locked due to a suspicious activity. Please send me a private message containing your username and password in order to reclaim your account."
            };

    public static String getRandomReply() {
        return MENTION[RandomGenerator.getRandomInt(MENTION.length)];
    }
}