package com.javadiscord.jdi.internal.models.invite;
public enum InviteTargetType {

    private final int value;

    InviteTargetType(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
