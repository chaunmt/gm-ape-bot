package me.chaunmt.gmapebot.utils;

import java.util.Random;

public class RandomGenerator {
    private static final Random rnd = new Random();

    public static int getRandomInt(int upperBound) {
        return getRandomInt(0, upperBound);
    }

    public static int getRandomInt(int lowerBound, int upperBound) {
        return rnd.nextInt(lowerBound, upperBound);
    }

    public static boolean getRandomBoolean() {
        return rnd.nextBoolean();
    }

}