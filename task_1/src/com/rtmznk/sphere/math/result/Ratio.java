package com.rtmznk.sphere.math.result;

/**
 * Created by RTM on 17.02.2017.
 */
public class Ratio {
    private double xySeparationRatio;
    private double xzSeparationRatio;
    private double yzSeparationRatio;

    public Ratio(double xySeparationRatio, double xzSeparationRatio, double yzSeparationRatio) {
        this.xySeparationRatio = xySeparationRatio;
        this.xzSeparationRatio = xzSeparationRatio;
        this.yzSeparationRatio = yzSeparationRatio;
    }

    public double getXySeparationRatio() {
        return xySeparationRatio;
    }

    public void setXySeparationRatio(double xySeparationRatio) {
        this.xySeparationRatio = xySeparationRatio;
    }

    public double getXzSeparationRatio() {
        return xzSeparationRatio;
    }

    public void setXzSeparationRatio(double xzSeparationRatio) {
        this.xzSeparationRatio = xzSeparationRatio;
    }

    public double getYzSeparationRatio() {
        return yzSeparationRatio;
    }

    public void setYzSeparationRatio(double yzSeparationRatio) {
        this.yzSeparationRatio = yzSeparationRatio;
    }
}
