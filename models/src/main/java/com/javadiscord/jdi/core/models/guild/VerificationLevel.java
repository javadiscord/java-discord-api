package com.javadiscord.jdi.core.models.guild;

import com.fasterxml.jackson.annotation.JsonValue;

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

    @JsonValue
    public int getValue() {
        return value;
    }
}
