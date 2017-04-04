package com.rtmznk.texthandler.composite;

import java.util.List;

/**
 * Created by RTM on 30.03.2017.
 */
public interface TextComponent {
    String recieveText();

    List<TextComponent> recieveChilds();

    boolean hasChilds();

    TextChildLevel level();

    List<TextComponent> recieveComponents(TextChildLevel level);
}
