package com.rtmznk.railway.entity;

/**
 * Created by RTM on 02.03.2017.
 */
public class CreatingEntityException extends Exception {
    public CreatingEntityException() {
        super();
    }

    public CreatingEntityException(String message) {
        super(message);
    }

    public CreatingEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreatingEntityException(Throwable cause) {
        super(cause);
    }
}
