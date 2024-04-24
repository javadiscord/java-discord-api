package com.javadiscord.jdi.internal.models.guild;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DefaultMessageNotificationLevel {
    ALL_MESSAGES(0),
    ONLY_MENTIONS(1);

    private final int value;

    DefaultMessageNotificationLevel(int value) {
        this.value = value;
    }

    @JsonValue
    public int value() {
        return value;
    }
}
