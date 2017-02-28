package test.com.rtmznk.sphere.storage;

import com.rtmznk.sphere.entity.Point;
import com.rtmznk.sphere.entity.Sphere;
import com.rtmznk.sphere.math.action.SphereCalculator;
import com.rtmznk.sphere.math.result.FullMathResult;
import com.rtmznk.sphere.storage.SingletonStorage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

/**
 * Created by RTM on 28.02.2017.
 */
public class SingletonStorageTest {
    private static SingletonStorage storage = SingletonStorage.getInstance();
    private static Sphere firstSphere;
    private static Sphere secondSphere;
    private static Sphere thirdSphere;

    @BeforeClass
    public static void initSpheres() {
        firstSphere = new Sphere(new Point(1, 2, 3), 4);
        secondSphere = new Sphere(new Point(10, 11, 12), 13);
        thirdSphere = new Sphere(new Point(1, 0, 0), 153);
    }

    @BeforeClass
    public static void fillInStorageWithTestParams() {
        storage.store(firstSphere);
        storage.store(secondSphere);
        storage.store(thirdSphere);
    }

    @AfterClass
    public static void unInitAll() {
        storage.clear();
        storage = null;
        firstSphere = null;
        secondSphere = null;
        thirdSphere = null;
    }

    @Test
    public void storeTest() {
        Sphere newSphere = new Sphere(new Point(6, 6, 6), 8);
        storage.store(newSphere);
        Assert.assertThat("Storage doesn't contain expected sphere id: ",
                storage.getStorage().keySet(), hasItem(newSphere.getId()));
    }

    @Test
    public void removeTest() {
        storage.remove(firstSphere);
        Assert.assertFalse("Storage contains firstSphere id: ",
                storage.getStorage().containsKey(firstSphere.getId()));
    }

    @Test
    public void getFullMathResultTest() {
        FullMathResult expected = SphereCalculator.getFullMathResult(secondSphere);
        FullMathResult actual = storage.getFullMathResult(secondSphere);
        Assert.assertEquals("Expected math result not equals to math result in storage",
                expected, actual);
    }
}
