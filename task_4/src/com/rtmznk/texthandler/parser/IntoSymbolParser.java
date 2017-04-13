package com.rtmznk.texthandler.parser;

import com.rtmznk.texthandler.composite.CompositeText;
import com.rtmznk.texthandler.composite.Symbol;
import com.rtmznk.texthandler.composite.TextComponent;

import java.util.Arrays;

class IntoSymbolParser extends ChainParser {
    TextComponent parse(String string) {
        CompositeText symbols = new CompositeText();
        Arrays.stream(string.split("")).forEach((String s) -> {
            s = s.intern();
            symbols.add(new Symbol(s));
        });
        return symbols;
    }
}
