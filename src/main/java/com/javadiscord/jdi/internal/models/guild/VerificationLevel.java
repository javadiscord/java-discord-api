package com.javadiscord.jdi.internal.models.guild;

public enum VerificationLevel {
    NONE(0),
    LOW(1),
    MEDIUM(2),
    HIGH(3),
    VERY_HIGH(4);

    private final int value;

    VerificationLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
