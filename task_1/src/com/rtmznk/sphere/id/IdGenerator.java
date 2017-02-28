package com.rtmznk.sphere.id;

/**
 * Created by RTM on 24.02.2017.
 */
public class IdGenerator {
    private static long id = 0;

    public static long getNextId() {
        return id++;
    }
}
