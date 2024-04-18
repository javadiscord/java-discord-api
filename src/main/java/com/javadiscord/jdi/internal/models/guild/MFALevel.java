package com.javadiscord.jdi.internal.models.guild;

public enum MFALevel {
    NONE(0),
    ELEVATED(1);

    private final int value;

    MFALevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
