package com.javadiscord.jdi.internal.models.application;
public enum ApplicationRoleConnectionMetadataType {

    private final int value;

    ApplicationRoleConnectionMetadataType(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
