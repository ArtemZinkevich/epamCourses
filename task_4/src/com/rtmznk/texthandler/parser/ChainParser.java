package com.rtmznk.texthandler.parser;

import com.rtmznk.texthandler.composite.TextComponent;

/**
 * Created by RTM on 08.04.2017.
 */
abstract class ChainParser {
   abstract TextComponent parse(String text);
}
