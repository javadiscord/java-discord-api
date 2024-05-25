package com.javadiscord.jdi.core.models.guild;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum InteractionType {
    PING,
    APPLICATION_COMMAND,
    MESSAGE_COMPONENT,
    APPLICATION_COMMAND_AUTOCOMPLETE,
    MODAL_SUBMIT;

    @JsonCreator
    public static PrivacyLevel fromIndex(int index) {
        return PrivacyLevel.values()[index - 1];
    }
}
