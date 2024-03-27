package com.javadiscord.discord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WelcomeScreenChannel {
    @JsonProperty("channel_id")
    private long channelId;

    private String description;

    @JsonProperty("emoji_id")
    private long emojiId;

    @JsonProperty("emoji_name")
    private String emojiName;
}
