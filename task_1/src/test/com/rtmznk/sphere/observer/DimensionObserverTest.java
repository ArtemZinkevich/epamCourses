package test.com.rtmznk.sphere.observer;

import com.rtmznk.sphere.entity.Point;
import com.rtmznk.sphere.entity.Sphere;
import com.rtmznk.sphere.math.action.SphereCalculator;
import com.rtmznk.sphere.math.result.FullMathResult;
import com.rtmznk.sphere.observer.DimensionObserver;
import com.rtmznk.sphere.storage.SingletonStorage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by RTM on 28.02.2017.
 */
public class DimensionObserverTest {
    @AfterClass
    public static void unDoChanges(){
        SingletonStorage.getInstance().clear();
    }
    @Test
    public void handleEventTest(){
        Sphere sphere = new Sphere(new Point(1,2,3),4);
        DimensionObserver dimensionObserver = new DimensionObserver();
        sphere.setObserver(dimensionObserver);
        SingletonStorage.getInstance().store(sphere);
        sphere.setRadius(10);
        FullMathResult expected = SphereCalculator.getFullMathResult(sphere);
        FullMathResult actual = SingletonStorage.getInstance().getFullMathResult(sphere);
        Assert.assertEquals("Expected result not equals to actual",expected,actual);
    }
}
