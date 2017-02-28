package test.com.rtmznk.sphere.math.action;

import com.rtmznk.sphere.entity.Point;
import com.rtmznk.sphere.entity.Sphere;
import com.rtmznk.sphere.math.action.SphereCalculator;
import com.rtmznk.sphere.math.result.Ratio;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by RTM on 27.02.2017.
 */
public class SphereCalculatorTest {
    public static Sphere testSphere;

    @BeforeClass
    public static void initTestSphere() {
        testSphere = new Sphere(new Point(1, 1, 1), 3);
    }

    @AfterClass
    public static void unInitSphere() {
        testSphere = null;
    }

    @Test
    public void calculateVolumeTest() {
        double expected = 4 / 3 * Math.PI * Math.pow(testSphere.getRadius(), 3);
        double actual = SphereCalculator.calculateVolume(testSphere);
        Assert.assertEquals("Wrong volume for sphere with radius: "
                + testSphere.getRadius(), expected, actual, 0.0001);
    }

    @Test
    public void calculateSquareTest() {
        double expected = 4 * Math.PI * Math.pow(testSphere.getRadius(), 2);
        double actual = SphereCalculator.calculateSquare(testSphere);
        Assert.assertEquals("Wrong square for sphere with radius: "
                + testSphere.getRadius(), expected, actual, 0.0001);
    }

    @Test
    public void calculateRatioTest() {
        double diametr = testSphere.getRadius() * 2;
        double xySeparationHeight = testSphere.getRadius() - testSphere.getCenter().getZ();
        double expectedXySeparationVolumeFirst = Math.PI * Math.pow(xySeparationHeight, 2)
                * (testSphere.getRadius() - 1d / 3 * xySeparationHeight);
        double expectedXySeparationVolumeSecond = Math.PI * Math.pow(diametr - xySeparationHeight, 2)
                * (testSphere.getRadius() - 1d / 3 * (diametr - xySeparationHeight));
        double expectedXySeparationRatio = expectedXySeparationVolumeFirst
                / expectedXySeparationVolumeSecond;
        double xzSeparationHeight = testSphere.getRadius() - testSphere.getCenter().getY();
        double expectedXzSeparationVolumeFirst = Math.PI * Math.pow(xzSeparationHeight, 2)
                * (testSphere.getRadius() - 1d / 3 * (xzSeparationHeight));
        double expectedXzSeparationVolumeSecond = Math.PI * Math.pow(diametr - xzSeparationHeight, 2)
                * (testSphere.getRadius() - 1d / 3 * (diametr - xzSeparationHeight));
        double expectedXzSeparationRatio = expectedXzSeparationVolumeFirst
                / expectedXzSeparationVolumeSecond;
        double yzSeparationHeight = testSphere.getRadius() - testSphere.getCenter().getX();
        double expectedYzSeparationVolumeFirst = Math.PI * Math.pow(yzSeparationHeight, 2)
                * (testSphere.getRadius() - 1d / 3 * (yzSeparationHeight));
        double expectedYzSeparationVolumeSecond = Math.PI * Math.pow(diametr - yzSeparationHeight, 2)
                * (testSphere.getRadius() - 1d / 3 * (diametr - yzSeparationHeight));
        double expectedYzSeparationRatio = expectedYzSeparationVolumeFirst / expectedYzSeparationVolumeSecond;
        Ratio expectedRatio = new Ratio(expectedXySeparationRatio, expectedXzSeparationRatio,
                expectedYzSeparationRatio);
        Ratio actualRatio = SphereCalculator.calculateRatio(testSphere);

        Assert.assertEquals("Wrong Ratio for sphere : "
                + testSphere, expectedRatio, actualRatio);
    }

}
