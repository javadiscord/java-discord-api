package com.javadiscord.jdi.core.models.channel;

public enum AllowedMentionsType {
    EVERYONE("everyone"),
    ROLES("roles"),
    USERS("users");

    private final String value;

    AllowedMentionsType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
