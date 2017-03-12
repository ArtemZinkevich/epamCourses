package test.com.rtmznk.railway.comparator;

import com.rtmznk.railway.comparator.*;
import com.rtmznk.railway.entity.CreatingEntityException;
import com.rtmznk.railway.entity.Locomotive;
import com.rtmznk.railway.entity.Train;
import com.rtmznk.railway.entity.Wagon;
import com.rtmznk.railway.generator.WagonNumberGen;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by RTM on 09.03.2017.
 */
public class ChainedComparatorTest {
    private static Train testTrain;
    private static ChainedComparator<Wagon> chainedComparator;

    @BeforeClass
    public static void initChainedComparator() throws CreatingEntityException {
        chainedComparator = new ChainedComparator<>();
    }

    @BeforeClass
    public static void initTrain() throws CreatingEntityException {
        List<Wagon> wagonList = new ArrayList<>(Arrays.asList(
                new Wagon(2, 50, 900),
                new Wagon(2, 30, 900),
                new Wagon(2, 40, 800),
                new Wagon(1, 30, 400),
                new Wagon(1, 30, 300),
                new Wagon(1, 20, 300),
                new Wagon(3, 80, 2000),
                new Wagon(3, 50, 2100),
                new Wagon(3, 50, 4000),
                new Wagon(1, 20, 300),
                new Wagon(3, 80, 2000)));

        List<Locomotive> locomotives =
                new ArrayList<>(Arrays.asList(new Locomotive(2, 8000)));
        WagonNumberGen.refreshNumber();
        testTrain = new Train(0, locomotives, wagonList);
    }

    @AfterClass
    public static void unInitTestClassStaticFields() {
        testTrain = null;
        chainedComparator = null;
    }

    @Test
    public void wagonTypeThanWagonNumberComparatorChainTest() throws CreatingEntityException {
        Comparator<Wagon> current = chainedComparator.getChainedComparator(new WagonTypeComparator(),
                new WagonNumberComparator());
        testTrain.getWagons().sort(current);
        Wagon firstWagonExpected = new Wagon(1, 30, 400);
        firstWagonExpected.setWagonNumber(4);
        Wagon lastWagonExpected = new Wagon(3, 80, 2000);
        lastWagonExpected.setWagonNumber(11);
        Assert.assertTrue("Wrong order of wagons.",
                firstWagonExpected.equals(testTrain.getWagons().get(0)) &&
                        lastWagonExpected.equals(testTrain.getWagons().get(testTrain.getWagons().size() - 1)));

    }

    @Test
    public void luggageWeightThanPassengersAmountComparatorChainTest() throws CreatingEntityException {
        Comparator<Wagon> current = chainedComparator.getChainedComparator(new WagonLuggageComparator(),
                new WagonPassengerComparator());
        testTrain.getWagons().sort(current);
        Wagon firstWagonExpected = new Wagon(1, 20, 300);
        firstWagonExpected.setWagonNumber(6);
        Wagon lastWagonExpected = new Wagon(3, 50, 4000);
        lastWagonExpected.setWagonNumber(9);
        Assert.assertTrue("Wrong order of wagons..",
                firstWagonExpected.equals(testTrain.getWagons().get(0)) &&
                        lastWagonExpected.equals(testTrain.getWagons().get(testTrain.getWagons().size() - 1)));
    }

    @Test
    public void passengersThanLuggageThanTypeThanNumberComparatorChainTest() throws CreatingEntityException {
        Comparator<Wagon> current = chainedComparator.getChainedComparator(new WagonPassengerComparator(),
                new WagonLuggageComparator(), new WagonTypeComparator(), new WagonNumberComparator());
        testTrain.getWagons().sort(current);
        Wagon firstWagonExpected = new Wagon(1, 20, 300);
        firstWagonExpected.setWagonNumber(6);
        Wagon lastWagonExpected = new Wagon(3, 80, 2000);
        lastWagonExpected.setWagonNumber(7);
        Assert.assertTrue("Wrong order of wagons.",
                firstWagonExpected.equals(testTrain.getWagons().get(0)) &&
                        lastWagonExpected.equals(testTrain.getWagons().get(testTrain.getWagons().size() - 1)));
    }
}

