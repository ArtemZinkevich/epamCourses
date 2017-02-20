package com.rtmznk.sphere.math.result;

/**
 * Created by RTM on 17.02.2017.
 */
public class FullMathResult {
    private Ratio ratio;
    private Square square;
    private Volume volume;

    public FullMathResult(Ratio ratio, Square square, Volume volume) {
        this.ratio = ratio;
        this.square = square;
        this.volume = volume;
    }

    public Ratio getRatio() {
        return ratio;
    }

    public Square getSquare() {
        return square;
    }

    public Volume getVolume() {
        return volume;
    }

    public void setRatio(Ratio ratio) {
        this.ratio = ratio;
    }

    public void setSquare(Square square) {
        this.square = square;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }
}
