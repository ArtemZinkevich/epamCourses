package com.rtmznk.sphere.observer;

/**
 * Created by RTM on 23.02.2017.
 */
public class GeneralizedEvent <T> {
    private T source;

    public GeneralizedEvent(T source) {
        this.source = source;
    }

    public T getSource() {
        return source;
    }
}
