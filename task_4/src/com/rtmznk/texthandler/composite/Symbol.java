package com.rtmznk.texthandler.composite;


/**
 * Created by RTM on 01.04.2017.
 */
public class Symbol implements TextComponent {
    private String content;

    public Symbol(String content) {
        this.content = content;
    }

    @Override
    public String receiveText() {
        return content;
    }

    @Override
    public TextChildLevel level() {
        return TextChildLevel.SYMBOL;
    }

}
