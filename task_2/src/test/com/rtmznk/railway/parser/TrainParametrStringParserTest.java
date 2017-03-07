package test.com.rtmznk.railway.parser;

import com.rtmznk.railway.parser.TrainParametrStringParser;
import com.rtmznk.railway.parser.ParametrsType;
import org.junit.*;

import java.util.*;

/**
 * Created by RTM on 06.03.2017.
 */
public class TrainParametrStringParserTest {
    private static TrainParametrStringParser parser;
    private List<String> correctStringList;
    private List<String> incorrectStringList;
    private Map<ParametrsType, List<int[]>> resultMap;

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
        resultMap = parser.getParsingResultMap(correctStringList);
        int[] trainExpectedArray = new int[]{0};
        int[] locomotiveExpectedArray = new int[]{0, 3300};
        int[] wagonExpectedArray = new int[]{1, 16, 10};
        int[] trainActualArray = resultMap.get(ParametrsType.TRAIN).get(0);
        int[] locomotiveActualArray = resultMap.get(ParametrsType.LOCOMOTIVE).get(0);
        int[] wagonActualArray = resultMap.get(ParametrsType.WAGON).get(0);
        Assert.assertArrayEquals("Expected not equals to actual : ",
                trainExpectedArray, trainActualArray);
        Assert.assertArrayEquals("Expected not equals to actual : ",
                locomotiveExpectedArray, locomotiveActualArray);
        Assert.assertArrayEquals("Expected not equals to actual : ",
                wagonExpectedArray, wagonActualArray);
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
        Assert.fail("Exception must occure");
    }
}
