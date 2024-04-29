package com.javadiscord.jdi.core.models.voice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VoiceRegion(
        String id, String name, boolean optimal, boolean deprecated, boolean custom) {}
