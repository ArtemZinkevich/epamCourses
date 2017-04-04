package com.rtmznk.texthandler.entity;

import com.rtmznk.texthandler.composite.TextChildLevel;
import com.rtmznk.texthandler.composite.TextComponent;

import java.util.List;

/**
 * Created by RTM on 01.04.2017.
 */
public class Symbol implements TextComponent {
    private String content;

    public Symbol(String content) {
        this.content = content;
    }

    @Override
    public String recieveText() {
        return content;
    }

    @Override
    public List<TextComponent> recieveChilds() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasChilds() {
        return false;
    }

    @Override
    public TextChildLevel level() {
        return TextChildLevel.SYMBOL;
    }

    @Override
    public List<TextComponent> recieveComponents(TextChildLevel level) {
        throw new UnsupportedOperationException();
    }
}
