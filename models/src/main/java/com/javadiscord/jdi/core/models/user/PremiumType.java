package com.javadiscord.jdi.core.models.user;

public enum PremiumType {
    NONE(0),
    NITRO_CLASSIC(1),
    NITRO(2),
    NITRO_BASIC(3);

    private final int id;

    PremiumType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
