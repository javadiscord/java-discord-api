package com.javadiscord.jdi.core.models.guild;

import com.javadiscord.jdi.core.models.application.ApplicationCommandOption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InteractionData(
    @JsonProperty("id") long id,
    @JsonProperty("name") String name,
    @JsonProperty("type") int type,
    @JsonProperty("resolved") ResolvedData resolved,
    @JsonProperty("options") ApplicationCommandOption[] options,
    @JsonProperty("guild_id") long guildId,
    @JsonProperty("target_id") long targetId
) {}
