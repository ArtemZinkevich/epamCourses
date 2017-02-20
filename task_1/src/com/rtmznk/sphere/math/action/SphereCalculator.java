package com.rtmznk.sphere.math.action;


import com.rtmznk.sphere.entity.CoordinateAxisPlane;
import com.rtmznk.sphere.entity.Sphere;
import com.rtmznk.sphere.math.result.FullMathResult;
import com.rtmznk.sphere.math.result.Ratio;
import com.rtmznk.sphere.math.result.Square;
import com.rtmznk.sphere.math.result.Volume;

/**
 * Created by RTM on 17.02.2017.
 */
public class SphereCalculator {
    public static Volume calculateVolume(Sphere sphere) {
        return new Volume(4 / 3 * Math.PI * Math.pow(sphere.getRadius(), 3));
    }

    public static Square calculateSquare(Sphere sphere) {
        return new Square(4 * Math.PI * Math.pow(sphere.getRadius(), 2));
    }

    public static Ratio calculateRatio(Sphere sphere) {
        double xySeparationRatio = RatioCalculationHelper.calculateOneRatio(sphere, CoordinateAxisPlane.XY_PLANE);
        double xzSeparationRatio = RatioCalculationHelper.calculateOneRatio(sphere, CoordinateAxisPlane.XZ_PLANE);
        double yzSeparationRatio = RatioCalculationHelper.calculateOneRatio(sphere, CoordinateAxisPlane.YZ_PLANE);
        return new Ratio(xySeparationRatio, xzSeparationRatio, yzSeparationRatio);
    }

    public static FullMathResult getFullMathResult(Sphere sphere) {
        return new FullMathResult(calculateRatio(sphere), calculateSquare(sphere), calculateVolume(sphere));
    }
}
