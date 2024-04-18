package com.javadiscord.jdi.internal.models.guild;

public enum ExplicitContentFilterLevel {
    DISABLED(0),
    MEMBERS_WITHOUT_ROLES(1),
    ALL_MEMBERS(2);

    private final int value;

    ExplicitContentFilterLevel(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
