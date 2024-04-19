package com.javadiscord.jdi.internal.models.message;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StickerType {
    STANDARD(1),
    GUILD(2);

    private final int value;

    StickerType(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}
