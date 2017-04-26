package test.com.rtmznk.xmltask.sax;

import com.rmznk.xmltask.sax.FlowersSAXBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FlowerSAXBuilderTest {
    private static String TEST_FILE_PATH = "data/plants.xml";
    private FlowersSAXBuilder builder;
    @Before
    public void initBuilder(){
        builder = new FlowersSAXBuilder();
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
