package com.javadiscord.jdi.core.models.guild;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum InteractionContext {
    GUILD,
    BOT_DM,
    PRIVATE_CHANNEL;

    @JsonCreator
    public static PrivacyLevel fromIndex(int index) {
        return PrivacyLevel.values()[index - 1];
    }
}
