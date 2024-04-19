package com.javadiscord.jdi.internal.models.guild;
public enum VerificationLevel {

    private final int value;

    VerificationLevel(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
