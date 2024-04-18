package com.javadiscord.jdi.internal.models.guild;

public enum DefaultMessageNotificationLevel {
    ALL_MESSAGES(0),
    ONLY_MENTIONS(1);

    private final int value;

    DefaultMessageNotificationLevel(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
