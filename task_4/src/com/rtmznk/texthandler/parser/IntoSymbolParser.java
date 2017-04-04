package com.rtmznk.texthandler.parser;

import com.rtmznk.texthandler.entity.Symbol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by RTM on 02.04.2017.
 */
class IntoSymbolParser {
    List<Symbol> parse(String string) {
        List<Symbol> symbols = new ArrayList<>();
        Arrays.stream(string.split("")).forEach((String s) -> {
            s.intern();
            symbols.add(new Symbol(s));
        });
        return symbols;
    }
}
