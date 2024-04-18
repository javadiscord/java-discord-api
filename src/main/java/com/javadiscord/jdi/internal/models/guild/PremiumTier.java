package com.javadiscord.jdi.internal.models.guild;

public enum PremiumTier {
    NONE(0),
    TIER_1(1),
    TIER_2(2),
    TIER_3(3);

    private final int value;

    PremiumTier(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
