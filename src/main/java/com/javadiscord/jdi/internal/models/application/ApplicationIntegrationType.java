package com.javadiscord.jdi.internal.models.application;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ApplicationIntegrationType {
    GUILD_INSTALL(0),
    USER_INSTALL(1);

    private final int value;

    ApplicationIntegrationType(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}
