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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FullMathResult that = (FullMathResult) o;

        if (Double.compare(that.square, square) != 0) return false;
        if (Double.compare(that.volume, volume) != 0) return false;
        return ratio != null ? ratio.equals(that.ratio) : that.ratio == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = ratio != null ? ratio.hashCode() : 0;
        temp = Double.doubleToLongBits(square);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(volume);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
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
