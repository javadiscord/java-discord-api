package com.javadiscord.jdi.internal.models.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Overwrite(long id, int type, String allow, String deny) {}
