package com.rtmznk.sphere.validator;

/**
 * Created by RTM on 21.02.2017.
 */
public class ArguementsValidator {
    public static boolean isArgumentsValid(int... args) {
        if (args.length == 4) {
            int radius = args[3];
            return radius >= 0 ? true : false;
        }
        return false;
    }
}
