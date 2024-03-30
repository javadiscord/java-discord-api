package com.javadiscord.jdi.internal.models.guild;

public enum WebhookType {
    INCOMING(1),
    CHANNEL_FOLLOWER(2),
    APPLICATION(3);

    private final int id;

    WebhookType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
