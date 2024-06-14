package com.javadiscord.jdi.core.models.scheduled_event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ScheduledEntityType {
    STAGE_INSTANCE,
    VOICE,
    EXTERNAL;

    @JsonCreator
    public static ScheduledEntityType fromIndex(int index) {
        return ScheduledEntityType.values()[index - 1];
    }

    @JsonValue
    public int toValue() {
        return ordinal() + 1;
    }

}
