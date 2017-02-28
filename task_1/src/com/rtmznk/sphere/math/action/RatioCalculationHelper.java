package com.rtmznk.sphere.math.action;

import com.rtmznk.sphere.entity.CoordinateAxisPlane;
import com.rtmznk.sphere.entity.Sphere;

/**
 * Created by RTM on 17.02.2017.
 */
public class RatioCalculationHelper {
    static double calculateOneRatio(Sphere sphere, CoordinateAxisPlane plane) {
        int radius = sphere.getRadius();
        int diametr = radius * 2;
        if (isIntersected((sphere), plane)) {
            double firstPartVolume = calculateSphereSegmentVolume(calculateSegmentHeight(sphere, plane), radius);
            double secondPartVolume = calculateSphereSegmentVolume(diametr
                    - calculateSegmentHeight(sphere, plane), radius);
            return firstPartVolume / secondPartVolume;
        }
        return 0;
    }

    private static double calculateSphereSegmentVolume(double segmentHeight, double radius) {
        return Math.PI * Math.pow(segmentHeight, 2) * (radius - 1d / 3 * segmentHeight);
    }

    private static int calculateHeight(int coordinate, int radius) {
        return coordinate < 0 ? (coordinate + radius) : (Math.abs(coordinate - radius));
    }

    private static int calculateSegmentHeight(Sphere sphere, CoordinateAxisPlane plane) {
        int x = sphere.getCenter().getX();
        int y = sphere.getCenter().getY();
        int z = sphere.getCenter().getZ();
        int radius = sphere.getRadius();
        switch (plane) {
            case XY_PLANE: {
                return calculateHeight(z, radius);
            }
            case XZ_PLANE: {
                return calculateHeight(y, radius);
            }
            case YZ_PLANE: {
                return calculateHeight(x, radius);
            }
            default:
                throw new IllegalArgumentException();
        }
    }

    private static boolean compareDistanceWithRadius(int coordinate, int radius) {
        return coordinate < 0 ? ((coordinate + radius) >= 0) : ((coordinate - radius) <= 0);
    }

    private static boolean isIntersected(Sphere sphere, CoordinateAxisPlane plane) {
        int x = sphere.getCenter().getX();
        int y = sphere.getCenter().getY();
        int z = sphere.getCenter().getZ();
        int radius = sphere.getRadius();
        switch (plane) {
            case XY_PLANE: {
                return compareDistanceWithRadius(z, radius);
            }
            case XZ_PLANE: {
                return compareDistanceWithRadius(y, radius);
            }
            case YZ_PLANE: {
                return compareDistanceWithRadius(x, radius);
            }
            default:
                throw new IllegalArgumentException();
        }
    }
}
