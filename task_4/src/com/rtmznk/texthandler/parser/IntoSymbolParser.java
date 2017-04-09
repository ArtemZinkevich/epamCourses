package com.rtmznk.texthandler.parser;

import com.rtmznk.texthandler.composite.CompositeText;
import com.rtmznk.texthandler.composite.TextComponent;
import com.rtmznk.texthandler.entity.Symbol;

import java.util.Arrays;

/**
 * Created by RTM on 02.04.2017.
 */
class IntoSymbolParser extends ChainParser{
    TextComponent parse(String string) {
       CompositeText symbols = new CompositeText();
        Arrays.stream(string.split("")).forEach((String s) -> {
            s.intern();
            symbols.add(new Symbol(s));
        });
        return symbols;
    }
}
