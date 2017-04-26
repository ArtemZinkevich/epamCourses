package com.rmznk.xmltask.entity;

public class GrowingTips {

    private float temperature;
    private boolean photophilous;
    private int watering;

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float value) {
        this.temperature = value;
    }

    public boolean isPhotophilous() {
        return photophilous;
    }

    public void setPhotophilous(boolean value) {
        this.photophilous = value;
    }

    public int getWatering() {
        return watering;
    }

    public void setWatering(int value) {
        this.watering = value;
    }

    @Override
    public String toString() {
        return "GrowingTips{" +
                "temperature=" + temperature +
                ", photophilous=" + photophilous +
                ", watering=" + watering +
                '}';
    }
}
