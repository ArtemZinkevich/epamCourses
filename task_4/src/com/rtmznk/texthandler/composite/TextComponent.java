package com.rtmznk.texthandler.composite;

import java.util.List;

/**
 * Created by RTM on 30.03.2017.
 */
public interface TextComponent {
    String recieveText();

    default List<TextComponent> recieveChilds() {
        throw new UnsupportedOperationException();
    }


    default boolean hasChilds() {
        return false;
    }

    TextChildLevel level();

    default List<TextComponent> recieveComponents(TextChildLevel level) {
        throw new UnsupportedOperationException();
    }
}
