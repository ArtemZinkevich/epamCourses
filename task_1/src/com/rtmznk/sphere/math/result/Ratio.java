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

    @Override
    public String toString() {
        return "Ratio{" +
                "xySeparationRatio=" + xySeparationRatio +
                ", xzSeparationRatio=" + xzSeparationRatio +
                ", yzSeparationRatio=" + yzSeparationRatio +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ratio ratio = (Ratio) o;

        if (Double.compare(ratio.xySeparationRatio, xySeparationRatio) != 0) return false;
        if (Double.compare(ratio.xzSeparationRatio, xzSeparationRatio) != 0) return false;
        return Double.compare(ratio.yzSeparationRatio, yzSeparationRatio) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(xySeparationRatio);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(xzSeparationRatio);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(yzSeparationRatio);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
