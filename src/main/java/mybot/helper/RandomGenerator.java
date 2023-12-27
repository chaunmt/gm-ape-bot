package mybot.helper;

import java.util.Random;

public class RandomGenerator
{
    public static int getInt(int upperBound) { return getInt(0, upperBound); }
    public static int getInt(int lowerBound, int upperBound)
    {
        Random rand = new Random(System.currentTimeMillis());
        return rand.nextInt(lowerBound, upperBound);
    }

    public static String getYesNo(int upperBound) { return getYesNo(0, upperBound); }
    public static String getYesNo(int lowerBound, int upperBound)
    {
        if (getInt(lowerBound, upperBound) % 2 == 0) return "Yes";
        return "No";
    }


}
