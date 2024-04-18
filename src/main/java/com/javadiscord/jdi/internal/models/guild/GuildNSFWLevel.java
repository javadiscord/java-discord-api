package com.javadiscord.jdi.internal.models.guild;

public enum GuildNSFWLevel {
    DEFAULT(0),
    EXPLICIT(1),
    SAFE(2),
    AGE_RESTRICTED(3);

    private final int value;

    GuildNSFWLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
