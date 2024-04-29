package com.javadiscord.jdi.core.models.channel;

import com.fasterxml.jackson.annotation.JsonValue;

public enum VideoQualityMode {
    AUTO(1),
    FULL(2);

    private final int value;

    VideoQualityMode(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}
