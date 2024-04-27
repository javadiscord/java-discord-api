package com.javadiscord.jdi.core.models.poll;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PollLayoutType {
    DEFAULT(1);

    private final int value;

    PollLayoutType(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}
