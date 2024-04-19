package com.javadiscord.jdi.internal.models.channel;

public enum VideoQualityMode {

    private final int value;

    VideoQualityMode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
