package com.javadiscord.jdi.internal.models.guild;

public enum IntegrationExpireBehaviour {
    REMOVE_ROLE(0),
    KICK(1);

    private final int value;

    private IntegrationExpireBehaviour(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
