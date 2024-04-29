package com.javadiscord.jdi.core.models.message;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MessageActivityType {
    JOIN(1),
    SPECTATE(2),
    LISTEN(3),
    JOIN_REQUEST(5);

    private final int value;

    MessageActivityType(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}
