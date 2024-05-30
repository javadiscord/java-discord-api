package com.javadiscord.jdi.internal;

public interface ReflectiveCommandOption {
    String name();

    String description();

    Enum<?> type();

    Object[] choices();

    boolean required();
}
