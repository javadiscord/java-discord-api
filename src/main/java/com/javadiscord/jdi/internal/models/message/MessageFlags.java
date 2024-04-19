package com.javadiscord.jdi.internal.models.message;

public enum MessageFlags {

    private final int value;

    MessageFlags(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
