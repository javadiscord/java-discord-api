package com.javadiscord.jdi.internal.models.poll;

public enum PollLayoutType {

    private final int value;

    PollLayoutType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
