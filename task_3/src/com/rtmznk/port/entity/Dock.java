package com.rtmznk.port.entity;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by RTM on 20.03.2017.
 */
public class Dock {
    private String name;
    private AtomicBoolean busy = new AtomicBoolean(false);

    public Dock(int i) {
        name = String.format("Dock#%d", i);
    }

    public boolean isBusy() {
        return busy.get();
    }

    public void occupyDock() {
        busy.set(true);
    }

    public void releaseDock() {
        busy.set(false);
    }

    @Override
    public String toString() {
        return name;
    }
}
