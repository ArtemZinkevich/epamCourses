package com.rtmznk.texthandler.composite;

import java.util.List;

/**
 * Created by RTM on 30.03.2017.
 */
public interface TextComponent {
    String receiveText();

    default List<TextComponent> receiveChilds() {
        throw new UnsupportedOperationException();
    }


    default boolean hasChilds() {
        return false;
    }

    TextChildLevel level();

    default List<TextComponent> receiveComponents(TextChildLevel level) {
        throw new UnsupportedOperationException();
    }

    default void removeChilds() {
        throw new UnsupportedOperationException();
    }

    default void add(TextComponent component) {
        throw new UnsupportedOperationException();
    }
}
