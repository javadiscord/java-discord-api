package com.javadiscord.jdi.core.models.channel;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ChannelFlags {
    PINNED(1),
    REQUIRE_TAG(1 << 4),
    HIDE_MEDIA_DOWNLOAD_OPTIONS(1 << 5);

    private final int id;

    ChannelFlags(int id) {
        this.id = id;
    }

    @JsonValue
    public int getId() {
        return id;
    }
}
