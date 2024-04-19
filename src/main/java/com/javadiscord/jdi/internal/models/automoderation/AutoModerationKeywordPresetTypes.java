package com.javadiscord.jdi.internal.models.automoderation;

public enum AutoModerationKeywordPresetTypes {
    PROFANITY(1),
    SEXUAL_CONTENT(2),
    SLURS(3);

    private final int value;

    AutoModerationKeywordPresetTypes(int value) {
        this.value = value;
    }
}
