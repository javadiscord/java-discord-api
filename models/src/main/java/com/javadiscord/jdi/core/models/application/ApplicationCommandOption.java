package com.javadiscord.jdi.core.models.application;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ApplicationCommandOption(
    @JsonProperty("name") String name,
    @JsonProperty("type") int type,
    @JsonProperty("value") Object value,
    @JsonProperty("options") List<ApplicationCommandOption> options,
    @JsonProperty("focused") boolean focused
) {

    public String valueAsString() {
        return String.valueOf(value);
    }

    public int valueAsInt() {
        return (int) value;
    }

    public double valueAsDouble() {
        return (double) value;
    }

    public boolean valueAsBoolean() {
        return (boolean) value;
    }

    @Override
    public String toString() {
        return String.valueOf(value == null ? "" : value);
    }
}
