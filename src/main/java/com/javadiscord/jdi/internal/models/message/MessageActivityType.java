package com.javadiscord.jdi.internal.models.message;

public enum MessageActivityType {

    private final int value;

    MessageActivityType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
