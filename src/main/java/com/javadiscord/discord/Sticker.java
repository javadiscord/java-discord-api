package com.javadiscord.discord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sticker {
    private long id;

    @JsonProperty("pack_id")
    private long packId;

    private String name;
    private String description;
    private String tags;
    private String asset;
    private int type;

    @JsonProperty("format_type")
    private int formatType;

    private boolean available;

    @JsonProperty("guild_id")
    private long guildId;

    private User user;

    @JsonProperty("sort_value")
    private int sortValue;
}
