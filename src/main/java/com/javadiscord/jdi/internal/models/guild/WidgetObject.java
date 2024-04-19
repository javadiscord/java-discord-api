package com.javadiscord.jdi.internal.models.guild;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record WidgetObject(
    @JsonProperty("id") long id,
    @JsonProperty("name") String name,
    @JsonProperty("instant_invite") String instantInvite,
    // @JsonProperty("channels") List<PartialChannel> channels, // TODO: future
    // developer implement these both
    // @JsonProperty("members") List<PartialUser> members,
    @JsonProperty("presence_count") int presenceCount
) {}
