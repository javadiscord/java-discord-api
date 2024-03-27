package com.javadiscord.discord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Connection {
    private String id;
    private String name;
    private String type;
    private boolean revoked;
    //private List<?> integration;
    private boolean verified;

    @JsonProperty("friend_sync")
    private boolean friendSync;

    @JsonProperty("show_activity")
    private boolean showActivity;

    @JsonProperty("two_way_link")
    private boolean twoWayLink;
    private int visibility;
}
