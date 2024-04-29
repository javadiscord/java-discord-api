package com.javadiscord.jdi.core.models.guild;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ExplicitContentFilterLevel {
    DISABLED(0),
    MEMBERS_WITHOUT_ROLES(1),
    ALL_MEMBERS(2);

    private final int value;

    ExplicitContentFilterLevel(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}
