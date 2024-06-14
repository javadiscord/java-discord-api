package com.javadiscord.jdi.core.models.guild;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum IntegrationType {
    TWITCH,
    YOUTUBE,
    DISCORD,
    GUILD_SUBSCRIPTION;

    @JsonCreator
    public static IntegrationType forValue(String value) {
        return IntegrationType.valueOf(value.toUpperCase());
    }

    @JsonValue
    public String toValue() {
        return name().toLowerCase();
    }
}
