package test.com.rtmznk.sphere.parser;

import com.rtmznk.sphere.parser.ParametrStringParser;
import org.junit.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;


/**
 * Created by RTM on 27.02.2017.
 */
public class ParametrStringParserTest {
    public ParametrStringParser parametrStringParser;
    List<String> testingStringList;

    @Before
    public void initParametrStringParser() {
        parametrStringParser = new ParametrStringParser();
    }

    @Before
    public void initTestList() {
        testingStringList = new ArrayList<>();
        testingStringList.add("1 2 3 4");
        testingStringList.add("SomeWrongString");
        testingStringList.add("3 4 5 6");
        testingStringList.add("Some 1 Wrong 2 String 3");
        testingStringList.add("6 7 8 9");
    }

    @After
    public void unInitAll() {
        parametrStringParser = null;
        testingStringList = null;
    }

    @Test
    public void getParsingResultListTest() {

        int[] firstExpected = new int[]{1, 2, 3, 4};
        int[] secondExpected = new int[]{3, 4, 5, 6};
        int[] thirdExpected = new int[]{6, 7, 8, 9};
        int expectedSize = 3;
        List<int[]> actual = parametrStringParser.getParsingResultList(testingStringList);
        Assert.assertEquals(expectedSize, actual.size());
        Iterator<int[]> actualIterator = actual.iterator();

        Assert.assertThat("Actual size not equals to expected size",
                actual.size(), equalTo(expectedSize));
        Assert.assertThat("Actual list contains not all expected data: "
                , actual, allOf(hasItem(firstExpected), hasItem(secondExpected),
                        hasItem(thirdExpected)));

    }

}
