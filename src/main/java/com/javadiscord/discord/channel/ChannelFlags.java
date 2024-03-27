package com.javadiscord.discord.channel;

public enum ChannelFlags {
    PINNED(1),
    REQUIRE_TAG(1 << 4),
    HIDE_MEDIA_DOWNLOAD_OPTIONS(1 << 5);

    private final int id;

    ChannelFlags(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
