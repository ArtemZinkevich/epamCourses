package test.com.rtmznk.texthandler.composite;

import com.rtmznk.texthandler.composite.CompositeText;
import com.rtmznk.texthandler.composite.Symbol;
import com.rtmznk.texthandler.composite.TextComponent;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

/**
 * Created by RTM on 13.04.2017.
 */
public class CompositeTextTest {
    private TextComponent testComponent;

    @Before
    public void init() {
        testComponent = new CompositeText();
        Stream.of("S", "t", "r", "i", "n", "g").map(Symbol::new).forEach(testComponent::add);
    }

    @After
    public void unInit() {
        testComponent = null;
    }

    @Test
    public void receiveTextTest() {
        String expected = "String";
        String actual = testComponent.receiveText().trim();
        Assert.assertEquals(expected, actual);
    }
}
