package com.javadiscord.jdi.core.models.stage;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StagePrivacy {
    PUBIC(0),
    GUILD_ONLY(2);

    private final int id;

    StagePrivacy(int id) {
        this.id = id;
    }

    @JsonValue
    public int getId() {
        return id;
    }
}
