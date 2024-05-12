package com.javadiscord.jdi.core.models.guild;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PrivacyLevel {
    PUBLIC,
    PRIVATE;

    @JsonCreator
    public static PrivacyLevel fromIndex(int index) {
        return PrivacyLevel.values()[index - 1];
    }
}
