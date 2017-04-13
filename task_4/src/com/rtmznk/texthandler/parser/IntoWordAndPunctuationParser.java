package com.rtmznk.texthandler.parser;

import com.rtmznk.texthandler.composite.CompositeText;
import com.rtmznk.texthandler.composite.TextChildLevel;
import com.rtmznk.texthandler.composite.TextComponent;
import com.rtmznk.texthandler.composite.Symbol;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by RTM on 02.04.2017.
 */
class IntoWordAndPunctuationParser extends ChainParser {
    private static final String WORD_OR_PUNCTUATION_REGEX = "(\\w+|\\p{Punct}+)";
    private static final String WORD_REGEX = "\\w+";
    private static final String PUNCTUATION_REGEX = "\\p{Punct}+";
    private static final String WORD_WITH_PUNCTUATION_REGEX = "\\w+\\p{Punct}+";
    private static final String PUNCTUATION_WITH_WORD_REGEX = "\\p{Punct}+\\w+";
    private ChainParser symbolParser;

    public IntoWordAndPunctuationParser() {
        symbolParser = new IntoSymbolParser();
    }

    TextComponent parse(String lexem) {
        Pattern wordAndPunctuationPattern = Pattern.compile(WORD_OR_PUNCTUATION_REGEX);
        Pattern wordPattern = Pattern.compile(WORD_REGEX);
        Pattern punctuationPattern = Pattern.compile(PUNCTUATION_REGEX);
        Matcher matcher = wordAndPunctuationPattern.matcher(lexem);
        CompositeText wordsAndPunctuation = new CompositeText();
        boolean spaceAfterWordNeeded = !Pattern.matches(WORD_WITH_PUNCTUATION_REGEX, lexem);
        boolean spaceAfterPunctuationNeeded = !Pattern.matches(PUNCTUATION_WITH_WORD_REGEX, lexem);
        while (matcher.find()) {
            String current = matcher.group();
            Matcher wordMatcher = wordPattern.matcher(current);
            Matcher punctuationMatcher = punctuationPattern.matcher(current);
            if (wordMatcher.find()) {
                String wordString = matcher.group();
                CompositeText word = new CompositeText();
                word.setLevel(TextChildLevel.WORD);
                symbolParser.parse(wordString).receiveChilds().forEach(word::add);
                wordsAndPunctuation.add(word);
                if (spaceAfterWordNeeded) {
                    wordsAndPunctuation.add(new Symbol(" "));
                }
            } else if (punctuationMatcher.find()) {
                String punctuationString = matcher.group();
                CompositeText punctuation = new CompositeText();
                punctuation.setLevel(TextChildLevel.PUNCTUATION);
                symbolParser.parse(punctuationString).receiveChilds().forEach(punctuation::add);
                wordsAndPunctuation.add(punctuation);
                if (spaceAfterPunctuationNeeded) {
                    wordsAndPunctuation.add(new Symbol(" "));
                }
            }
        }
        return wordsAndPunctuation;
    }
}
