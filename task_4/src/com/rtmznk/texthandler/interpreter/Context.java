package com.rtmznk.texthandler.interpreter;

import java.util.ArrayDeque;

/**
 * Created by RTM on 06.04.2017.
 */
class Context {
    private int i;
    private int j;
    private ArrayDeque<String> contextValues = new ArrayDeque<>();

    public Context(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    String popValue() {
        return contextValues.pop();
    }

    void pushValue(String value) {
        this.contextValues.push(value);
    }

    public int addAndGetI() {
        return ++i;
    }

    public int getAndAddI() {
        int result = i;
        i++;
        return result;
    }
    public int deductAndGetI() {
        return --i;
    }

    public int getAndDeductI() {
        int result = i;
        i--;
        return result;
    }
    public int addAndGetJ() {
        return ++j;
    }

    public int getAndAddJ() {
        int result = j;
        j++;
        return result;
    }
    public int deductAndGetJ() {
        return --j;
    }

    public int getAndDeductJ() {
        int result = j;
        j--;
        return result;
    }
}

