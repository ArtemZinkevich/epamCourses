package com.rtmznk.railway.generator;

/**
 * Created by RTM on 03.03.2017.
 */
public class WagonNumberGen {
    private static int number=1;
    public static int getNextWagonNumber(){
        return number++;
    }
    public static void refreshNumber(){
        number=1;
    }
}
