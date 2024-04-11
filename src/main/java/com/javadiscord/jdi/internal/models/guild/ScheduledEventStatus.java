package com.javadiscord.jdi.internal.models.guild;

public enum ScheduledEventStatus {
    SCHEDULED(1),
    ACTIVE(2),
    COMPLETED(3),
    CANCELED(4);
    private final int id;

    ScheduledEventStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
