package com.rtmznk.port.exception;

/**
 * Created by RTM on 20.03.2017.
 */
public class PortResourceException extends Exception{
    public PortResourceException() {
        super();
    }

    public PortResourceException(String message) {
        super(message);
    }

    public PortResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PortResourceException(Throwable cause) {
        super(cause);
    }
}
