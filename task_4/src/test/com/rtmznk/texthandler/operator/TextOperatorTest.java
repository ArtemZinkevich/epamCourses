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
import java.util.List;

/**
 * Created by RTM on 13.04.2017.
 */
public class TextOperatorTest {
    private static TextOperator operator;
    private TextComponent testText;
    private TextComponent sortTestText;
    private TextComponent removeTestText;
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

    @Before
    public void initSwapTestText() {
        testText = new CompositeText();
        CompositeText sentence = new CompositeText();
        sentence.setLevel(TextChildLevel.SENTENCE);
        CompositeText firstLexeme = new CompositeText();
        firstLexeme.setLevel(TextChildLevel.LEXEME);
        firstLexeme.add(new Symbol("f1 "));
        CompositeText secondLexeme = new CompositeText();
        secondLexeme.setLevel(TextChildLevel.LEXEME);
        secondLexeme.add(new Symbol("f2 "));
        CompositeText thirdLexeme = new CompositeText();
        thirdLexeme.setLevel(TextChildLevel.LEXEME);
        thirdLexeme.add(new Symbol("f3. "));
        sentence.add(firstLexeme);
        sentence.add(secondLexeme);
        sentence.add(thirdLexeme);
        testText.add(sentence);
    }

    @After
    public void unInitSwapTestText() {
        testText = null;
    }

    @Test
    public void swapFirstLastLexemeTest() {
        String expected = "f3. f2 f1 ";
        operator.swapFirstLastLexeme(testText);
        String actual = testText.receiveText();
        Assert.assertEquals(expected, actual);
    }

    @Before
    public void initSentenceSortTestText() {
        sortTestText = new CompositeText();
        CompositeText sentence = new CompositeText();
        sentence.setLevel(TextChildLevel.SENTENCE);
        CompositeText firstLexeme = new CompositeText();
        firstLexeme.setLevel(TextChildLevel.LEXEME);
        CompositeText sentenceTwo = new CompositeText();
        sentenceTwo.setLevel(TextChildLevel.SENTENCE);
        firstLexeme.add(new Symbol("f1. "));
        CompositeText secondLexeme = new CompositeText();
        secondLexeme.setLevel(TextChildLevel.LEXEME);
        secondLexeme.add(new Symbol("f2 "));
        CompositeText thirdLexeme = new CompositeText();
        thirdLexeme.setLevel(TextChildLevel.LEXEME);
        thirdLexeme.add(new Symbol("f3. "));
        sentence.add(firstLexeme);
        sentenceTwo.add(secondLexeme);
        sentenceTwo.add(thirdLexeme);
        sortTestText.add(sentenceTwo);
        sortTestText.add(sentence);
    }

    @After
    public void unInitSentenceSortTestText() {
        sortTestText = null;
    }

    @Test
    public void sentencesMinToMaxLexemesTest() {
        String expected = "f1. ";
        String expectedSecond = "f2 f3. ";
        List<TextComponent> actual = operator.sentencesMinToMaxLexemes(sortTestText);
        String firstActual = actual.get(0).receiveText();
        String secondActual = actual.get(1).receiveText();
        boolean isTrue = firstActual.equals(expected) && secondActual.equals(expectedSecond);
        Assert.assertTrue(isTrue);
    }

    @Before
    public void initRemoveTestText() {
        removeTestText = new CompositeText();
        CompositeText sentence = new CompositeText();
        sentence.setLevel(TextChildLevel.SENTENCE);
        CompositeText firstLexeme = new CompositeText();
        firstLexeme.setLevel(TextChildLevel.LEXEME);
        firstLexeme.add(new Symbol("first "));
        CompositeText secondLexeme = new CompositeText();
        secondLexeme.setLevel(TextChildLevel.LEXEME);
        secondLexeme.add(new Symbol("second "));
        CompositeText thirdLexeme = new CompositeText();
        thirdLexeme.setLevel(TextChildLevel.LEXEME);
        thirdLexeme.add(new Symbol("third. "));
        sentence.add(firstLexeme);
        sentence.add(secondLexeme);
        sentence.add(thirdLexeme);
        removeTestText.add(sentence);
    }

    @After
    public void unInitRemoveTestText() {
        removeTestText = null;
    }

    @Test
    public void removeLexemesTest() {
        operator.removeLexemes(removeTestText, 's', 7);
        String expected = "first third. ";
        String actual = removeTestText.receiveText();
        Assert.assertEquals(expected, actual);
    }
}
