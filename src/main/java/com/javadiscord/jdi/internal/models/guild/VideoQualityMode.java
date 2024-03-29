package com.javadiscord.jdi.internal.models.guild;

public enum VideoQualityMode {
    AUTO(1),
    FULL(2);

    private final int id;

    VideoQualityMode(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
