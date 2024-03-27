package com.javadiscord.discord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ThreadMetadata {
    private boolean archived;

    @JsonProperty("auto_archive_duration")
    private int autoArchiveDuration;

    @JsonProperty("archive_timestamp")
    private long archiveTimestamp;

    private boolean locked;
    private boolean invitable;

    @JsonProperty("create_timestamp")
    private String createTimestamp;
}
