package test.com.rtmznk.texthandler.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.com.rtmznk.texthandler.composite.CompositeTextTest;
import test.com.rtmznk.texthandler.interpreter.InterpreterTest;
import test.com.rtmznk.texthandler.operator.TextOperatorTest;
import test.com.rtmznk.texthandler.parser.MainTextParserTest;
import test.com.rtmznk.texthandler.reader.TextFileReaderTest;

/**
 * Created by RTM on 07.03.2017.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({CompositeTextTest.class, InterpreterTest.class,
        TextOperatorTest.class, MainTextParserTest.class, TextFileReaderTest.class})
public class SuiteTest {
}
