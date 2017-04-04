package com.rtmznk.texthandler.parser;

import com.rtmznk.texthandler.composite.CompositeText;
import com.rtmznk.texthandler.composite.TextChildLevel;
import com.rtmznk.texthandler.composite.TextComponent;
import com.rtmznk.texthandler.entity.Symbol;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by RTM on 02.04.2017.
 */
class IntoMathAndLexemParser {
    private static final String LINEFEED_REGEX = "\\s+";
    private static final String LEXEM_REGEX = "[\\w\\p{Punct}]*";
    private static final String MATH_REGEX = "(?:\\s(?:[\\d+\\-*/(]|[ij])" +
            "(?:[\\d+\\-*/(\\s)]|[ij\\s)])*(?!\\p{Alpha}))";
    private static final String MATH_OR_LEXEM_REGEX = "(?:(?:\\s(?:[\\d+\\-*\\/(]|[ij])(?:[\\d+\\-*\\/(\\s\\)]" +
            "|[ij\\s\\)])*(?!\\p{Alpha}))|((?<=\\s)[\\w\\p{Punct}]+)|( ?[\\n] +))";
    private static Logger logger = LogManager.getLogger(IntoMathAndLexemParser.class);
    private IntoSymbolParser symbolParser = new IntoSymbolParser();
    private IntoWordAndPunctuationParser wordAndPunctParser = new IntoWordAndPunctuationParser();

    TextComponent parse(String sentence) {
        Pattern textAndLexem = Pattern.compile(MATH_OR_LEXEM_REGEX);
        Matcher matcher = textAndLexem.matcher(sentence);
        CompositeText mathAndLexem = new CompositeText();
        while (matcher.find()) {
            String current = matcher.group();
            if (Pattern.matches(MATH_REGEX, current)) {
                CompositeText math = new CompositeText();
                math.setLevel(TextChildLevel.MATH);
                symbolParser.parse(current.trim()).forEach(math::add);
                mathAndLexem.add(math);
                mathAndLexem.add(new Symbol(" "));
            } else if (Pattern.matches(LEXEM_REGEX, current)) {
                CompositeText lexem = new CompositeText();
                lexem.setLevel(TextChildLevel.LEXEM);
                lexem.add(wordAndPunctParser.parse(current));
                mathAndLexem.add(lexem);
            } else if (Pattern.matches(LINEFEED_REGEX, current)) {
                mathAndLexem.add(new Symbol("\n"));
            } else {
                logger.log(Level.WARN, "Wrong sentence.Won't be parsed : " + current);
            }
        }
        return mathAndLexem;
    }
}
