package com.rtmznk.sphere.math.result;

/**
 * Created by RTM on 17.02.2017.
 */
public class FullMathResult {
    private Ratio ratio;
    private double square;
    private double volume;

    public FullMathResult(Ratio ratio, double square, double volume) {
        this.ratio = ratio;
        this.square = square;
        this.volume = volume;
    }

    public Ratio getRatio() {
        return ratio;
    }

    public void setRatio(Ratio ratio) {
        this.ratio = ratio;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }
}
