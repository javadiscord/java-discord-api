package com.javadiscord.jdi.core.models.message;

public enum Component {
    ACTION_ROW(1),
    BUTTON(2),
    STRING_SELECT(3),
    TEXT_INPUT(4),
    USER_SELECT(5),
    ROLE_SELECT(6),
    MENTIONABLE_SELECT(7),
    CHANNEL_SELECT(8);

    private final int type;

    Component(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
