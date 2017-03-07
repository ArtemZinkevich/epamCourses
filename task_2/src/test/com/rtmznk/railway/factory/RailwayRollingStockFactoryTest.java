package test.com.rtmznk.railway.factory;

import com.rtmznk.railway.entity.CreatingEntityException;
import com.rtmznk.railway.entity.Locomotive;
import com.rtmznk.railway.entity.Train;
import com.rtmznk.railway.entity.Wagon;
import com.rtmznk.railway.factory.RailwayRollingStockFactory;
import com.rtmznk.railway.parser.ParametrsType;
import org.junit.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;

/**
 * Created by RTM on 07.03.2017.
 */
public class RailwayRollingStockFactoryTest {
    private static RailwayRollingStockFactory rollingStockFactory;
    private List<int[]> testWagonParametrs;
    private List<int[]> testLocomotiveParametrs;
    private HashMap<ParametrsType, List<int[]>> testTrainParamMap;

    @BeforeClass
    public static void initFactory() {
        rollingStockFactory = new RailwayRollingStockFactory();
    }

    @Test
    public void createWagonTest() throws CreatingEntityException {
        Wagon expected = new Wagon(1, 2, 3);
        Wagon actual = rollingStockFactory.createWagon(1, 2, 3);
        Assert.assertEquals("expected wagon not equals to actual : ", expected, actual);
    }

    @Test
    public void createLocomotiveTest() throws CreatingEntityException {
        Locomotive expected = new Locomotive(1, 2);
        Locomotive actual = rollingStockFactory.createLocomotive(1, 2);
        Assert.assertEquals("expected locomotive not equals to actual", expected, actual);
    }

    @Before
    public void initTestWagonParams() {
        testWagonParametrs = Arrays.asList(new int[]{1, 2, 3}, new int[]{1, 2, 3});
    }

    @After
    public void unInitTestWagonParams() {
        testWagonParametrs = null;
    }

    @Test
    public void createWagonListTest() throws CreatingEntityException {
        Wagon firstExpected = new Wagon(1, 2, 3);
        Wagon secondExpected = new Wagon(1, 2, 3);
        List<Wagon> actual = rollingStockFactory.createWagonList(testWagonParametrs);
        Assert.assertThat(actual.size(), equalTo(2));
        Assert.assertThat(actual, hasItems(firstExpected, secondExpected));
    }

    @Before
    public void initTestLocomotiveParametrs() {
        testLocomotiveParametrs = Arrays.asList(new int[]{1, 2000}, new int[]{1, 3000});
    }

    @After
    public void unInitTestLocomotiveParametrs() {
        testLocomotiveParametrs = null;
    }

    @Test
    public void createLocomotiveListTest() throws CreatingEntityException {
        Locomotive firstExpected = new Locomotive(1, 2000);
        Locomotive secondExpected = new Locomotive(1, 3000);
        List<Locomotive> actual = rollingStockFactory.createLocomotiveList(testLocomotiveParametrs);
        Assert.assertEquals("Size of list doesn't match expected", actual.size(), 2);
        Assert.assertThat(actual, hasItems(firstExpected, secondExpected));
    }

    @Before
    public void initTestTrainParamMap() {
        testTrainParamMap = new HashMap<>();
        testTrainParamMap.put(ParametrsType.TRAIN, Arrays.asList(new int[]{0}));
        testTrainParamMap.put(ParametrsType.WAGON, Arrays.asList(new int[]{1, 20, 30}));
        testTrainParamMap.put(ParametrsType.LOCOMOTIVE, Arrays.asList(new int[]{1, 2000}));
    }

    @Test
    public void createTrainTest() throws CreatingEntityException {
        Train expectedTrain;
        List<Wagon> expectedWagonList = Arrays.asList(new Wagon(1, 20, 30));
        List<Locomotive> expectedLocomotiveList = Arrays.asList(new Locomotive(1, 2000));
        expectedTrain = new Train(0, expectedLocomotiveList, expectedWagonList);
        Train actualTrain = rollingStockFactory.createTrain(testTrainParamMap);
        Assert.assertEquals(expectedTrain, actualTrain);
    }

    @Test(expected = CreatingEntityException.class)
    public void createWagonFromInvalidWagonTypeParametrs() throws CreatingEntityException {
        rollingStockFactory.createWagon(100, 10, 300);
        Assert.fail("Exception must occure");
    }

    @Test(expected = CreatingEntityException.class)
    public void createWagonFromInvalidPassengersParametrs() throws CreatingEntityException {
        rollingStockFactory.createWagon(1, 200, 300);
        Assert.fail("Exception must occure");
    }

    @Test(expected = CreatingEntityException.class)
    public void createLocomotiveFromInvalidParametrs() throws CreatingEntityException {
        rollingStockFactory.createLocomotive(100, 20000);
        Assert.fail("Exception must occure");
    }

    @Test(expected = CreatingEntityException.class)
    public void createTrainFromInvalidMap() throws CreatingEntityException {
        HashMap<ParametrsType, List<int[]>> invalidMap = new HashMap<>();
        invalidMap.put(ParametrsType.TRAIN, Arrays.asList(new int[]{10}));
        invalidMap.put(ParametrsType.LOCOMOTIVE, Arrays.asList(new int[]{12, 1000}));
        invalidMap.put(ParametrsType.WAGON, Arrays.asList(new int[]{100, 120, 20}));
        rollingStockFactory.createTrain(invalidMap);
        Assert.fail("Exception must occure");
    }

}
