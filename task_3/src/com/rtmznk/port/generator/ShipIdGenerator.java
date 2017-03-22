package com.rtmznk.port.generator;

/**
 * Created by RTM on 21.03.2017.
 */
public class ShipIdGenerator {
    private static int id = 1;

    public static int getNextId() {
        return id++;
    }
}
