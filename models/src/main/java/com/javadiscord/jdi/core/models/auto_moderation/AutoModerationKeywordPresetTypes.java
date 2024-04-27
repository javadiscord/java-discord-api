package com.javadiscord.jdi.core.models.auto_moderation;

public enum AutoModerationKeywordPresetTypes {
    PROFANITY(1),
    SEXUAL_CONTENT(2),
    SLURS(3);

    private final int value;

    AutoModerationKeywordPresetTypes(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
