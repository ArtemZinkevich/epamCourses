package test.com.rtmznk.xmltask.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.com.rtmznk.xmltask.dom.FlowerDomBuilderTest;
import test.com.rtmznk.xmltask.sax.FlowerSAXBuilderTest;
import test.com.rtmznk.xmltask.stax.FlowerStAXBuilderTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({FlowerDomBuilderTest.class, FlowerSAXBuilderTest.class, FlowerStAXBuilderTest.class})
public class SuiteTest {
}
