package com.javadiscord.jdi.internal.models.auto_moderation;

public enum AutoModerationEventType {
    MESSAGE_SEND(1);

    private final int value;

    AutoModerationEventType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
