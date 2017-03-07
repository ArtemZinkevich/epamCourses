package test.com.rtmznk.railway.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.com.rtmznk.railway.factory.RailwayRollingStockFactoryTest;
import test.com.rtmznk.railway.operator.TrainOperatorTest;
import test.com.rtmznk.railway.parser.TrainParametrStringParserTest;
import test.com.rtmznk.railway.reader.TextFileReaderTest;

/**
 * Created by RTM on 07.03.2017.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({TrainOperatorTest.class, TrainParametrStringParserTest.class,
        TextFileReaderTest.class, RailwayRollingStockFactoryTest.class})
public class SuiteTest {
}
