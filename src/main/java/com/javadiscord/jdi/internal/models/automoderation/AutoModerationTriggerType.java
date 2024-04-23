package com.javadiscord.jdi.internal.models.automoderation;

public enum AutoModerationTriggerType {
    KEYWORD(1),
    SPAM(3),
    KEYWORD_PRESET(4),
    MENTION_SPAM(5);

    private final int value;

    AutoModerationTriggerType(int value) {
        this.value = value;
    }
}
