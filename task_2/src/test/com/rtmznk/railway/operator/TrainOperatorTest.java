package test.com.rtmznk.railway.operator;

import com.rtmznk.railway.comparator.WagonNumberComparator;
import com.rtmznk.railway.entity.CreatingEntityException;
import com.rtmznk.railway.entity.Locomotive;
import com.rtmznk.railway.entity.Train;
import com.rtmznk.railway.entity.Wagon;
import com.rtmznk.railway.operator.TrainOperator;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;

/**
 * Created by RTM on 07.03.2017.
 */
public class TrainOperatorTest {
    private static Train testTrain;

    @BeforeClass
    public static void initTrain() throws CreatingEntityException {
        List<Wagon> wagonList = new ArrayList<>(Arrays.asList(new Wagon(0, 10, 900),
                new Wagon(1, 20, 700),
                new Wagon(2, 40, 800),
                new Wagon(3, 80, 1400),
                new Wagon(4, 50, 2500),
                new Wagon(5, 0, 100000),
                new Wagon(6, 0, 0),
                new Wagon(1, 30, 2100),
                new Wagon(3, 50, 4000)));
        List<Locomotive> locomotives = Arrays.asList(new Locomotive(2, 8000));
        testTrain = new Train(0, locomotives, wagonList);
    }

    @AfterClass
    public static void unInitTestTrain() {
        testTrain = null;
    }

    @Test
    public void getWagonsWithPassengersInRangeTest() throws CreatingEntityException {
        Wagon expectedFirst = new Wagon(0, 10, 900);
        Wagon expectedSecond = new Wagon(1, 20, 700);
        Wagon expectedThird = new Wagon(2, 40, 800);
        Wagon expectedFourth = new Wagon(1, 30, 2100);
        List<Wagon> actualWagonList = TrainOperator.getWagonsWithPassengersInRange(testTrain, 10, 40);
        Assert.assertThat("Incorrect wagons from passenger diapsone [10,40] : ",
                actualWagonList, hasItems(expectedFirst, expectedSecond, expectedThird, expectedFourth));
    }

    @Test
    public void calculatePassengersAmountTest() {
        int expectedPassengersAmount = 280;
        int actualAmount = TrainOperator.calculatePassengers(testTrain);
        Assert.assertEquals("Actual amount not equals to expected : ",
                expectedPassengersAmount, actualAmount);
    }

    @Test
    public void calculateLuggageAmountTest() {
        int expectedLuggageWeight = 112400;
        int actualWeight = TrainOperator.calculateLuggage(testTrain);
        Assert.assertEquals("Actual amount not equals to expected : ",
                expectedLuggageWeight, actualWeight);
    }

    @Test
    public void sortTrainWagonsTest() {
        int expectedFirstWagonNumber = 1;
        int expectedLastWagonNumber = 9;
        TrainOperator.sortTrainWagons(testTrain, new WagonNumberComparator());
        int actualFirstWagonNumber = testTrain.getWagons().get(0).getWagonNumber();
        int actualLastWagonNumber = testTrain.getWagons().get(testTrain.getWagons().size() - 1).getWagonNumber();
        Assert.assertTrue("Actual first wagon not equals to expected first or" +
                        " actual last wagon not equals to expected last wagon.",
                actualFirstWagonNumber == expectedFirstWagonNumber &&
                        actualLastWagonNumber == expectedLastWagonNumber);
    }

    @Test
    public void removeAllIncorrectTypeWagonsTest() throws CreatingEntityException {
        Wagon incorrectTypeWagon = new Wagon(8, 0, 0);
        testTrain.addWagon(incorrectTypeWagon);
        TrainOperator.removeAllIncorrectTypeWagons(testTrain);
        Assert.assertThat("Wagon with incorrect type wasn't removed.",
                testTrain.getWagons(), not(hasItem(incorrectTypeWagon)));
    }
}
