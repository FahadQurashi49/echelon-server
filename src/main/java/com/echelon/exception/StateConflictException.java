package com.echelon.exception;

/**
 * Created by Fahad Qureshi on 4/18/2017.
 */
public class StateConflictException extends RuntimeException {
    public StateConflictException() {
        super();
    }

    public StateConflictException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public StateConflictException(final String message) {
        super(message);
    }

    public StateConflictException(final Throwable cause) {
        super(cause);
    }
}
