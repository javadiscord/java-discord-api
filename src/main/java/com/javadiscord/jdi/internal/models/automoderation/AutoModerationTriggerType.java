package com.javadiscord.jdi.internal.models.automoderation;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AutoModerationTriggerType {
    KEYWORD(1),
    SPAM(3),
    KEYWORD_PRESET(4),
    MENTION_SPAM(5);

    private final int value;

    AutoModerationTriggerType(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}
