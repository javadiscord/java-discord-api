package com.javadiscord.jdi.internal.exceptions;

public class InvalidBotTokenException extends RuntimeException {

    public InvalidBotTokenException() {
        super();
    }

    public InvalidBotTokenException(String message) {
        super(message);
    }
}
