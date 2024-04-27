package com.javadiscord.jdi.core.models.guild;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OnboardingPromptType {
    MULTIPLE_CHOICE(0),
    DROPDOWN(1);

    private final int value;

    OnboardingPromptType(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}
