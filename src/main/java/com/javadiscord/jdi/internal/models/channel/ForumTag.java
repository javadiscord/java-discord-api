package com.javadiscord.jdi.internal.models.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ForumTag(
    @JsonProperty("id") long id,
    @JsonProperty("name") String name,
    @JsonProperty("moderated") boolean moderated,
    @JsonProperty("emoji_id") long emojiId,
    @JsonProperty("emoji_name") String emojiName
) {}
