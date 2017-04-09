package com.rtmznk.texthandler.interpreter;

/**
 * Created by RTM on 08.04.2017.
 */
@FunctionalInterface
public interface Operation {
    void interpret(Context c);
}
