package test.com.rtmznk.texthandler.parser;



import com.rtmznk.texthandler.parser.MainTextParser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * Created by RTM on 06.03.2017.
 */
public class MainTextParserTest {
    private MainTextParser parser;
    private String testString;

    @Before
    public void init() {
        parser = new MainTextParser();
        testString = "\tOne string.";
    }

    @After
    public void unInitParser() {
        parser = null;
        testString =null;
    }

    @Test
    public void doChainTest() {
        String actual = parser.doChain(testString).receiveText().trim();
        String expected = testString.trim();
        Assert.assertEquals(expected,actual);
    }
}
