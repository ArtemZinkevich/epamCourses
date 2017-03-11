package test.com.rtmznk.railway.parser;

import com.rtmznk.railway.parser.TrainParametrStringParser;
import com.rtmznk.railway.parser.ParametrsType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * Created by RTM on 06.03.2017.
 */
public class TrainParametrStringParserTest {
    private static TrainParametrStringParser parser;
    private List<String> correctStringList;
    private List<String> incorrectStringList;

    @BeforeClass
    public static void initParametrStringParser() {
        parser = new TrainParametrStringParser();
    }

    @AfterClass
    public static void unInitParser() {
        parser = null;
    }

    @Before
    public void initCorrectStringList() {
        correctStringList = Arrays.asList("train{ type  : 0  }",
                "locomotive { engineTYpe :0  ,enginePower :3300}",
                "wagon{ TYpe  : 1 ,pasSenger :16, luggage:10}");
    }

    @After
    public void unInitCorrectStringList() {
        correctStringList = null;
    }

    @Test
    public void getParsingResultMapTest() {
        Map<ParametrsType, List<int[]>> resultMap = parser.getParsingResultMap(correctStringList);
        int[] trainExpectedArray = new int[]{0};
        int[] locomotiveExpectedArray = new int[]{0, 3300};
        int[] wagonExpectedArray = new int[]{1, 16, 10};
        int[] trainActualArray = resultMap.get(ParametrsType.TRAIN).get(0);
        int[] locomotiveActualArray = resultMap.get(ParametrsType.LOCOMOTIVE).get(0);
        int[] wagonActualArray = resultMap.get(ParametrsType.WAGON).get(0);
        Assert.assertTrue("Actual parsing result not equals to expected.",
                Arrays.equals(trainExpectedArray, trainActualArray) &&
                        Arrays.equals(locomotiveExpectedArray, locomotiveActualArray) &&
                        Arrays.equals(wagonExpectedArray, wagonActualArray));
    }

    @Before
    public void initIncorrectStringList() {
        incorrectStringList = Arrays.asList("wrong string",
                "wrong string",
                "wrong string");
    }

    @After
    public void unInitIncorrectStringList() {
        incorrectStringList = null;
    }

    @Test(expected = RuntimeException.class)
    public void getParsingResultMapFromIncorrectStringListTest() {
        parser.getParsingResultMap(incorrectStringList);
    }
}
