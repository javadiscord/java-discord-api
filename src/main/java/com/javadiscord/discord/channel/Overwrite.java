package com.javadiscord.discord.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Overwrite(long id, int type, String allow, String deny) {}
