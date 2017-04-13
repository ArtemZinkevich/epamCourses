package test.com.rtmznk.texthandler.operator;

import com.rtmznk.texthandler.composite.CompositeText;
import com.rtmznk.texthandler.composite.Symbol;
import com.rtmznk.texthandler.composite.TextChildLevel;
import com.rtmznk.texthandler.composite.TextComponent;
import com.rtmznk.texthandler.interpreter.Context;
import com.rtmznk.texthandler.interpreter.Interpreter;
import com.rtmznk.texthandler.operator.TextOperator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by RTM on 13.04.2017.
 */
public class TextOperatorTest {
    private static TextOperator operator;
    private TextComponent mathExpression;
    private Interpreter interpreter;
    private Context context;

    @BeforeClass
    public static void initOperator() {
        operator = new TextOperator();
    }

    @AfterClass
    public static void unInitOperator() {
        operator = null;
    }

    @Before
    public void initMathExpressions() {
        mathExpression = new CompositeText();
        CompositeText expression = new CompositeText();
        expression.setLevel(TextChildLevel.MATH);
        Arrays.stream("2+2*2".split("")).map(Symbol::new).forEach(expression::add);
        mathExpression.add(new Symbol("A"));
        mathExpression.add(expression);
        mathExpression.add(new Symbol("A"));
        interpreter = new Interpreter();
        context = new Context(0, 0);
    }

    @After
    public void unInitMathExpression() {
        mathExpression = null;
        interpreter = null;
        context = null;
    }

    @Test
    public void calculateTextTest() {
        operator.calculateText(mathExpression, interpreter, context);
        String expected = "A6.0A";
        String actual = mathExpression.receiveText();
        Assert.assertEquals(expected, actual);
    }
}
