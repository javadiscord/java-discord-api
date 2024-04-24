package com.javadiscord.jdi.internal.models.channel;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ForumLayoutType {
    NOT_SET(0),
    LIST_VIEW(1),
    GALLERY_VIEW(2);

    private final int value;

    ForumLayoutType(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }
}
