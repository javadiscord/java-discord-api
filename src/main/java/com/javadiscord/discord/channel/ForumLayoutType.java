package com.javadiscord.discord.channel;

public enum ForumLayoutType {
    NOT_SET(0),
    LIST_VIEW(1),
    GALLERY_VIEW(2);

    private final int id;

    ForumLayoutType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
