package com.javadiscord.discord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Emoji {
    private long id;
    private String name;
    private List<Role> roles;
    private User user;

    @JsonProperty("require_colons")
    private boolean requiresColons;

    private boolean managed;
    private boolean animated;
    private boolean available;
}
