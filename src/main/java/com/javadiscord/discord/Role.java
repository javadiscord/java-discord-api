package com.javadiscord.discord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Role {
    private long id;
    private String name;
    private String color;
    private boolean hoist;
    private String icon;

    @JsonProperty("unicode_emoji")
    private String unicodeEmoji;

    private int position;
    private String permissions;
    private boolean managed;
    private boolean mentionable;
    private Tags tags;
    private int flags;
}
