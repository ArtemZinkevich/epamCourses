package test.com.rtmznk.sphere.task;

import com.rtmznk.sphere.entity.Point;
import com.rtmznk.sphere.entity.Sphere;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.*;

/**
 * Created by RTM on 28.02.2017.
 */
@RunWith(Parameterized.class)
public class TaskTest {
    private Object object;

    public TaskTest(Object object) {
        this.object = object;
    }

    @Parameterized.Parameters
    public static Collection<Object> stepUpCoefficientTableValues() {
        return Arrays.asList(new Object[]{new Sphere(new Point(1, 2, 3), 4),
                new Sphere(new Point(1, 1, 1), 4),
                new Sphere(new Point(2, 2, 2), 4)
        });
    }

    @Test
    public void isObjectSphereTest() {
        Assert.assertThat("object is not sphere", object, instanceOf(Sphere.class));
    }

    @Test
    public void isSphereTouchesCoordinatePlanesTest() {
        Sphere sphere = (Sphere) object;
        boolean condition = Math.abs(sphere.getCenter().getX()) <= sphere.getRadius() ||
                Math.abs(sphere.getCenter().getY()) <= sphere.getRadius() ||
                Math.abs(sphere.getCenter().getZ()) <= sphere.getRadius();
        Assert.assertTrue("Sphere don't touch CoordinatePlanes", condition);
    }
}
