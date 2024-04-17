package com.javadiscord.jdi.internal.models.stage;

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
