package com.javadiscord.jdi.internal.models.message;

public enum MessageType {

    private final int value;

    MessageType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
