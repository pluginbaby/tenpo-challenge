package com.challenge.tenpo.rest.exceptions;

public class ExceedMaxAttemptsException extends Exception {

    public ExceedMaxAttemptsException() {
    }

    public ExceedMaxAttemptsException(final String message) {
        super(message);
    }

    public ExceedMaxAttemptsException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
