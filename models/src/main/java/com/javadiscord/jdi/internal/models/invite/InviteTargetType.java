package com.javadiscord.jdi.internal.models.invite;

import com.fasterxml.jackson.annotation.JsonValue;

public enum InviteTargetType {
    STREAM(1),
    EMBEDDED_APPLICATION(2);

    private final int value;

    InviteTargetType(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}
