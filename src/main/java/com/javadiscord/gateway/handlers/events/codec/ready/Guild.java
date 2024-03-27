package com.javadiscord.gateway.handlers.events.codec.ready;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Guild(boolean unavailable, String id) {}
