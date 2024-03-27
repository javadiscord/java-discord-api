package com.javadiscord.gateway.handlers.events.ready;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Guild(boolean unavailable, String id) {}
