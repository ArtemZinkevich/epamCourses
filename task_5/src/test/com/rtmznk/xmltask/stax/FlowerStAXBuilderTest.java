package test.com.rtmznk.xmltask.stax;


import com.rmznk.xmltask.stax.FlowerStAXBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by RTM on 27.04.2017.
 */
public class FlowerStAXBuilderTest {
    private static String TEST_FILE_PATH = "data/plants.xml";
    private FlowerStAXBuilder builder;
    @Before
    public void initBuilder(){
        builder = new FlowerStAXBuilder();
    }
    @After
    public void unInitBuilder(){
        builder=null;
    }
    @Test
    public void buildListFlowersTest(){
        int expectedSize=16;
        builder.buildListFlowers(TEST_FILE_PATH);
        Assert.assertEquals(builder.getFlowers().size(),expectedSize);
    }

}