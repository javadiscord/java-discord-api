package com.javadiscord.jdi.core.models.application;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ApplicationRoleConnectionMetadataType {
    INTEGER_LESS_THAN_OR_EQUAL(1),
    INTEGER_GREATER_THAN_OR_EQUAL(2),
    INTEGER_EQUAL(3),
    INTEGER_NOT_EQUAL(4),
    DATETIME_LESS_THAN_OR_EQUAL(5),
    DATETIME_GREATER_THAN_OR_EQUAL(6),
    BOOLEAN_EQUAL(7),
    BOOLEAN_NOT_EQUAL(8);

    private final int value;

    ApplicationRoleConnectionMetadataType(int value) {
        this.value = value;
    }

    @JsonValue
    public int value() {
        return value;
    }
}
