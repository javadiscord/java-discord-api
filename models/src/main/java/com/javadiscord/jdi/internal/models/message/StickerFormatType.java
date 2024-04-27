package com.javadiscord.jdi.internal.models.message;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StickerFormatType {
    PNG(1),
    APNG(2),
    LOTTIE(3),
    GIF(4);

    private final int value;

    StickerFormatType(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}
