package com.javadiscord.jdi.core.api.builders.command;

public enum CallbackResponseType {
    PONG(1),
    CHANNEL_MESSAGE_WITH_SOURCE(4),
    DEFERRED_CHANNEL_MESSAGE_WITH_SOURCE(5),
    DEFERRED_UPDATE_MESSAGE(6),
    UPDATE_MESSAGE(7),
    APPLICATION_COMMAND_AUTOCOMPLETE_RESULT(8),
    MODAL(9),
    PREMIUM_REQUIRED(10);

    private final int value;

    CallbackResponseType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
