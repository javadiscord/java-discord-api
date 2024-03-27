package com.javadiscord.discord;

public enum StagePrivacy {
    PUBIC(0),
    GUILD_ONLY(2);
    private final int id;

    StagePrivacy(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
