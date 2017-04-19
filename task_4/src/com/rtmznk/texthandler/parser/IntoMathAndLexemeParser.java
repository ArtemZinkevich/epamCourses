package com.rtmznk.texthandler.parser;

import com.rtmznk.texthandler.composite.CompositeText;
import com.rtmznk.texthandler.composite.Symbol;
import com.rtmznk.texthandler.composite.TextChildLevel;
import com.rtmznk.texthandler.composite.TextComponent;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by RTM on 02.04.2017.
 */
class IntoMathAndLexemeParser extends ChainParser {
    private static final String LINEFEED_REGEX = "\\s+";
    private static final String LEXEME_REGEX = "[\\w\\p{Punct}]*";
    private static final String MATH_REGEX = "(?:\\s(?:[\\d+\\-*/(]|[ij])" +
            "(?:[\\d+\\-*/(\\s)]|[ij\\s)])*(?!\\p{Alpha}))";
    private static final String MATH_OR_LEXEME_REGEX = "(?:(?:\\s(?:[\\d+\\-*\\/(]|[ij])(?:[\\d+\\-*\\/(\\s\\)]" +
            "|[ij\\s\\)])*(?!\\p{Alpha}))|((?<=\\s)[\\w\\p{Punct}]+)|( ?[\\n] +))";
    private static Logger logger = LogManager.getLogger(IntoMathAndLexemeParser.class);
    private ChainParser symbolParser;
    private ChainParser wordAndPunctuationParser;

    IntoMathAndLexemeParser() {
        symbolParser = new IntoSymbolParser();
        wordAndPunctuationParser = new IntoWordAndPunctuationParser();
    }

    TextComponent parse(String sentence) throws TextFormatException {
        Pattern textAndLexeme = Pattern.compile(MATH_OR_LEXEME_REGEX);
        Matcher matcher = textAndLexeme.matcher(sentence);
        CompositeText mathAndLexeme = new CompositeText();
        while (matcher.find()) {
            String current = matcher.group();
            if (Pattern.matches(MATH_REGEX, current)) {
                CompositeText math = new CompositeText();
                math.setLevel(TextChildLevel.MATH);
                symbolParser.parse(current.trim()).receiveChilds().forEach(math::add);
                mathAndLexeme.add(math);
                mathAndLexeme.add(new Symbol(" "));
            } else if (Pattern.matches(LEXEME_REGEX, current)) {
                CompositeText lexeme = new CompositeText();
                lexeme.setLevel(TextChildLevel.LEXEME);
                lexeme.add(wordAndPunctuationParser.parse(current));
                mathAndLexeme.add(lexeme);
            } else if (Pattern.matches(LINEFEED_REGEX, current)) {
                mathAndLexeme.add(new Symbol("\n"));
            } else {
                logger.log(Level.WARN, "Wrong sentence.Won't be parsed : " + current);
            }
        }
        return mathAndLexeme;
    }
}
