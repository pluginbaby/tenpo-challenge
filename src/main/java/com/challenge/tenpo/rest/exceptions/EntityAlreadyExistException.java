package com.challenge.tenpo.rest.exceptions;

public class EntityAlreadyExistException extends RuntimeException{

    public EntityAlreadyExistException(final String message) {
        super(message);
    }

    public EntityAlreadyExistException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public EntityAlreadyExistException(final Throwable cause) {
        super(cause);
    }
}
