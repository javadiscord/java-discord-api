package com.javadiscord.jdi.core.api.builders.command;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CommandOptionType {
    SUB_COMMAND(1),
    SUB_COMMAND_GROUP(2),
    STRING(3),
    INTEGER(4),
    BOOLEAN(5),
    USER(6),
    CHANNEL(7),
    ROLE(8),
    MENTIONABLE(9),
    NUMBER(10),
    ATTACHMENT(11),
    ;

    private final int value;

    CommandOptionType(int value) {
        this.value = value;
    }

    @JsonValue
    public int getValue() {
        return value;
    }

    public static CommandOptionType fromName(String name) {
        for (CommandOptionType type : CommandOptionType.values()) {
            if (type.name().equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown command option type: " + name);
    }
}
