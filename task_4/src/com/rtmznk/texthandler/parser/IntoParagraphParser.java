package com.rtmznk.texthandler.parser;

import com.rtmznk.texthandler.composite.CompositeText;
import com.rtmznk.texthandler.composite.Symbol;
import com.rtmznk.texthandler.composite.TextChildLevel;
import com.rtmznk.texthandler.composite.TextComponent;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntoParagraphParser extends ChainParser {
    private static final String PARAGRAPH_REGEX = "(?s)\\t[\\p{Upper}+\\-(](.(?!\\r?\\n\\r?\\n))*.";
    private ChainParser sentenceParser;

    public IntoParagraphParser() {
        sentenceParser = new IntoSentenceParser();
    }

    TextComponent parse(String text) {
        CompositeText allParagraphs = new CompositeText();
        ArrayList<CompositeText> paragraphList = new ArrayList<>();
        Pattern paragraphPattern = Pattern.compile(PARAGRAPH_REGEX);
        Matcher paragraphMatcher = paragraphPattern.matcher(text);
        while (paragraphMatcher.find()) {
            String paragraph = paragraphMatcher.group();
            CompositeText newParagraph = new CompositeText();
            newParagraph.setLevel(TextChildLevel.PARAGRAPH);
            newParagraph.add(new Symbol("\t"));
            newParagraph.add(sentenceParser.parse(paragraph));
            paragraphList.add(newParagraph);
        }
        for (int i = 0; i < paragraphList.size() - 1; i++) {
            CompositeText current = paragraphList.get(i);
            current.add(new Symbol("\n"));
            current.add(new Symbol("\n"));
            allParagraphs.add(current);
        }
        allParagraphs.add(paragraphList.get(paragraphList.size() - 1));
        return allParagraphs;
    }

    public TextComponent doChain(String text) {
        return parse(text);
    }
}
