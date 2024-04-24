package com.javadiscord.jdi.internal.models.automoderation;

public enum AutoModerationEventType {
    MESSAGE_SEND(1);

    private final int value;

    AutoModerationEventType(int value) {
        this.value = value;
    }
}
