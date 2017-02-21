package com.rtmznk.sphere.exception;

/**
 * Created by RTM on 21.02.2017.
 */
public class InvalidArgumentsException  extends  Exception{

    public InvalidArgumentsException() {
        super();
    }

    public InvalidArgumentsException(String message) {
        super(message);
    }

    public InvalidArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidArgumentsException(Throwable cause) {
        super(cause);
    }

    protected InvalidArgumentsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
