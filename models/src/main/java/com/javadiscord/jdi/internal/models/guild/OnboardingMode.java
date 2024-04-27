package com.javadiscord.jdi.internal.models.guild;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OnboardingMode {
    DEFAULT(0),
    ADVANCED(1);

    private final int value;

    OnboardingMode(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}
