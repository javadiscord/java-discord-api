package com.javadiscord.jdi.internal.models.guild;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GuildNSFWLevel {
    DEFAULT(0),
    EXPLICIT(1),
    SAFE(2),
    AGE_RESTRICTED(3);

    private final int value;

    GuildNSFWLevel(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}