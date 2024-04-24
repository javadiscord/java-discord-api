package com.javadiscord.jdi.internal.models.channel;

import com.fasterxml.jackson.annotation.JsonValue;

public enum SortOrderType {
    LATEST_ACTIVITY(0),
    CREATION_DATE(1);

    private final int value;

    SortOrderType(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}
