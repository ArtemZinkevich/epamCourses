package test.com.rtmznk.xmltask.dom;

import com.rmznk.xmltask.dom.FlowerDOMBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by RTM on 27.04.2017.
 */
public class FlowerDomBuilderTest {

    private static String TEST_FILE_PATH = "data/plants.xml";
    private FlowerDOMBuilder builder;

    @Before
    public void initBuilder() {
        builder = new FlowerDOMBuilder();
    }

    @After
    public void unInitBuilder() {
        builder = null;
    }

    @Test
    public void buildListFlowersTest() {
        int expectedSize = 16;
        builder.buildListFlowers(TEST_FILE_PATH);
        Assert.assertEquals(builder.getFlowers().size(), expectedSize);
    }

}
