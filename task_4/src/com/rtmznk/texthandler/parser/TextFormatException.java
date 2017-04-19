package com.rtmznk.texthandler.parser;

/**
 * Created by RTM on 19.04.2017.
 */
public class TextFormatException extends Exception {
    public TextFormatException() {
        super();
    }

    public TextFormatException(String message) {
        super(message);
    }

    public TextFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public TextFormatException(Throwable cause) {
        super(cause);
    }
}
