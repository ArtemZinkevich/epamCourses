package test.com.rtmznk.railway.comparator;

import com.rtmznk.railway.comparator.*;
import com.rtmznk.railway.entity.CreatingEntityException;
import com.rtmznk.railway.entity.Locomotive;
import com.rtmznk.railway.entity.Train;
import com.rtmznk.railway.entity.Wagon;
import com.rtmznk.railway.generator.WagonNumberGen;
import org.junit.*;

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
    public static void initChainComparator() throws CreatingEntityException {
        chainedComparator = new ChainedComparator<>();
    }

    @BeforeClass
    public static void initTrain() throws CreatingEntityException {
        List<Wagon> wagonList = new ArrayList<>(Arrays.asList(new Wagon(2, 50, 900),
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
        Assert.assertEquals("First wagon must be wagon with min wagon type. If exist few - with min number",
                firstWagonExpected, testTrain.getWagons().get(0));
        Assert.assertEquals("First wagon must be wagon with max wagon type. If exist few - with max number",
                lastWagonExpected, testTrain.getWagons().get(testTrain.getWagons().size() - 1));
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
        Assert.assertEquals("First wagon must be wagon with min luggage weight." +
                        "If exist few - with min passengers amount",
                firstWagonExpected, testTrain.getWagons().get(0));
        Assert.assertEquals("First wagon must be wagon with min luggage weight." +
                        "If exist few - with min passengers amount",
                lastWagonExpected, testTrain.getWagons().get(testTrain.getWagons().size() - 1));
    }

    @Before
    public void addAdditionalTestWagonsToTestTrain() throws CreatingEntityException {
        Wagon equalToFirstDifferentInNumber = new Wagon(1, 20, 300);
        equalToFirstDifferentInNumber.setWagonNumber(10);
        Wagon equalToLastDifferentInNumber = new Wagon(3, 80, 2000);
        equalToLastDifferentInNumber.setWagonNumber(11);
        testTrain.getWagons().add(equalToFirstDifferentInNumber);
        testTrain.getWagons().add(equalToLastDifferentInNumber);
    }

    @Test
    public void PassengersThanLuggageThanTypeThanNumberComparatorChainTest() throws CreatingEntityException {
        Comparator<Wagon> current = chainedComparator.getChainedComparator(new WagonPassengerComparator(),
                new WagonLuggageComparator(), new WagonTypeComparator(), new WagonNumberComparator());
        testTrain.getWagons().sort(current);
        Wagon firstWagonExpected = new Wagon(1, 20, 300);
        firstWagonExpected.setWagonNumber(6);
        Wagon lastWagonExpected = new Wagon(3, 80, 2000);
        lastWagonExpected.setWagonNumber(7);
        Assert.assertEquals("First wagon must be wagon : " + firstWagonExpected,
                firstWagonExpected, testTrain.getWagons().get(0));
        Assert.assertEquals("Lasst wagon must be wagon : " + lastWagonExpected,
                lastWagonExpected, testTrain.getWagons().get(testTrain.getWagons().size() - 1));
    }
}

