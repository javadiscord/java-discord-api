package com.javadiscord.jdi.internal.models.guild;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MFALevel {
    NONE(0),
    ELEVATED(1);

    private final int value;

    MFALevel(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}