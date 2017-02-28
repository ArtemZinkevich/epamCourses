package test.com.rtmznk.sphere.creator;

import com.rtmznk.sphere.creator.SphereCreator;
import com.rtmznk.sphere.creator.exception.CreatingSphereException;
import com.rtmznk.sphere.entity.Point;
import com.rtmznk.sphere.entity.Sphere;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by RTM on 28.02.2017.
 */
public class SphereCreatorTest {
    public static SphereCreator creator;
    public List<int[]> testArguments;

    @BeforeClass
    public static void initSphereCreator() {
        creator = new SphereCreator();
    }

    @AfterClass
    public static void unInitSphereCreator() {
        creator = null;
    }

    @Test
    public void createSphereTest() throws CreatingSphereException {
        Sphere expected = new Sphere(new Point(1, 2, 3), 4);
        Sphere actual = creator.createSphere(1, 2, 3, 4);
        Assert.assertEquals(expected, actual);
    }

    @Before
    public void initTestArguments() {
        testArguments = new ArrayList<>();
        int[] first = {1, 2, 3, 4};
        int[] second = {1, 2, 3, -4};
        testArguments.add(first);
        testArguments.add(second);
    }

    @After
    public void unInitTestArguments() {
        testArguments = null;
    }

    @Test
    public void createSphereListTest() {
        Sphere expectedSphere = new Sphere(new Point(1, 2, 3), 4);
        List<Sphere> actual = creator.createSphereList(testArguments);
        Assert.assertThat("Wrong actual list size",actual.size(),equalTo(1));
        Assert.assertThat("actual list doesn't contain expectedSphere",actual,hasItem(expectedSphere));
    }

    @Test(expected = CreatingSphereException.class)
    public void createSphereWithWrongParametrsAmountTest() throws CreatingSphereException {
        creator.createSphere(1, 2, 3);
        Assert.fail("Exception must occure");
    }

    @Test(expected = CreatingSphereException.class)
    public void createSphereWithNegativeRadiusTest() throws CreatingSphereException {
        creator.createSphere(1, 2, 3, -4);
        Assert.fail("Exception must occure");
    }
}
