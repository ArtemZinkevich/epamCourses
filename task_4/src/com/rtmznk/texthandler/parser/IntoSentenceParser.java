package com.rtmznk.texthandler.parser;

import com.rtmznk.texthandler.composite.CompositeText;
import com.rtmznk.texthandler.composite.TextChildLevel;
import com.rtmznk.texthandler.composite.TextComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by RTM on 02.04.2017.
 */
class IntoSentenceParser extends ChainParser {
    private static final String SENTENCE_REGEX = "(?s)\\s[\\p{Upper}+\\-(](.(?!\\.))*..";
    private ChainParser mathAndLexemeParser;

    IntoSentenceParser() {
        mathAndLexemeParser = new IntoMathAndLexemeParser();
    }

    TextComponent parse(String paragraph) throws TextFormatException {
        CompositeText allSentences = new CompositeText();
        Pattern sentencePattern = Pattern.compile(SENTENCE_REGEX);
        Matcher sentenceMatcher = sentencePattern.matcher(paragraph);
        while (sentenceMatcher.find()) {
            CompositeText sentence = new CompositeText();
            sentence.setLevel(TextChildLevel.SENTENCE);
            String current = sentenceMatcher.group();
            sentence.add(mathAndLexemeParser.parse(current));
            allSentences.add(sentence);
        }
        return allSentences;
    }
}
