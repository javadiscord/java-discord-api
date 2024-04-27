package com.javadiscord.jdi.internal.models.guild;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.jdi.internal.models.application.ApplicationCommandOption;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InteractionData(
        @JsonProperty("id") long id,
        @JsonProperty("name") String name,
        @JsonProperty("type") int type,
        @JsonProperty("resolved") ResolvedData resolved,
        @JsonProperty("options") ApplicationCommandOption[] options,
        @JsonProperty("guild_id") Long guildId,
        @JsonProperty("target_id") Long targetId) {}
