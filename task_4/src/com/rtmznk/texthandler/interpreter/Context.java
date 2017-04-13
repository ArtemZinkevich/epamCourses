package com.rtmznk.texthandler.interpreter;

import java.util.ArrayDeque;

/**
 * Created by RTM on 06.04.2017.
 */
public class Context {
    private int i;
    private int j;
    private ArrayDeque<String> contextValues = new ArrayDeque<>();

    public Context(int i, int j) {
        this.i = i;
        this.j = j;
    }

     int getI() {
        return i;
    }

     int getJ() {
        return j;
    }

    String popValue() {
        return contextValues.pop();
    }

    void pushValue(String value) {
        this.contextValues.push(value);
    }

     int addAndGetI() {
        return ++i;
    }

     int getAndAddI() {
        int result = i;
        i++;
        return result;
    }

     int deductAndGetI() {
        return --i;
    }

     int getAndDeductI() {
        int result = i;
        i--;
        return result;
    }

     int addAndGetJ() {
        return ++j;
    }

     int getAndAddJ() {
        int result = j;
        j++;
        return result;
    }

     int deductAndGetJ() {
        return --j;
    }

     int getAndDeductJ() {
        int result = j;
        j--;
        return result;
    }

    public void resetContext() {
        contextValues.clear();
    }
}

