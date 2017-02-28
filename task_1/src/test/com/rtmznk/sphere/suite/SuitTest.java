package test.com.rtmznk.sphere.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.com.rtmznk.sphere.creator.SphereCreatorTest;
import test.com.rtmznk.sphere.math.action.SphereCalculatorTest;
import test.com.rtmznk.sphere.observer.DimensionObserverTest;
import test.com.rtmznk.sphere.parser.ParametrStringParserTest;
import test.com.rtmznk.sphere.reader.TextFileReaderTest;
import test.com.rtmznk.sphere.storage.SingletonStorageTest;
import test.com.rtmznk.sphere.task.TaskTest;

/**
 * Created by RTM on 28.02.2017.
 */
@Suite.SuiteClasses({SphereCreatorTest.class, SphereCalculatorTest.class,
        ParametrStringParserTest.class, TextFileReaderTest.class,
        SingletonStorageTest.class, TaskTest.class, DimensionObserverTest.class})
@RunWith(Suite.class)
public class SuitTest {
}
