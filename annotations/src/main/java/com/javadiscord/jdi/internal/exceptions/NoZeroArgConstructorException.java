package com.javadiscord.jdi.internal.exceptions;

public class NoZeroArgConstructorException extends RuntimeException {

    public NoZeroArgConstructorException() {
        super();
    }

    public NoZeroArgConstructorException(String message) {
        super(message);
    }

}
