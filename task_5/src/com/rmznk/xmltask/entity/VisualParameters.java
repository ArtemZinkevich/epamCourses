package com.rmznk.xmltask.entity;

public class VisualParameters {

    private String stalkColor;
    private String leavesColor;
    private int averageSize;

    public String getStalkColor() {
        return stalkColor;
    }

    public void setStalkColor(String value) {
        this.stalkColor = value;
    }

    public String getLeavesColor() {
        return leavesColor;
    }

    public void setLeavesColor(String value) {
        this.leavesColor = value;
    }

    public int getAverageSize() {
        return averageSize;
    }

    public void setAverageSize(int value) {
        this.averageSize = value;
    }

    @Override
    public String toString() {
        return "VisualParameters{" +
                "stalkColor='" + stalkColor + '\'' +
                ", leavesColor='" + leavesColor + '\'' +
                ", averageSize=" + averageSize +
                '}';
    }
}
