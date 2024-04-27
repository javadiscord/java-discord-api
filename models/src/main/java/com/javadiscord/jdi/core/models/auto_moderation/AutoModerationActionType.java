package com.javadiscord.jdi.core.models.auto_moderation;

public enum AutoModerationActionType {
    BLOCKED_MESSAGE(1),
    SEND_ALERT_MESSAAGE(2),
    TIMEOUT(3);

    private final int value;

    AutoModerationActionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
