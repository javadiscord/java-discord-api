package com.javadiscord.discord;

public enum SortOrderType {
    LATEST_ACTIVITY(0),
    CREATION_DATE(1);
    private final int id;

    SortOrderType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
