package com.javadiscord.jdi.internal.models.channel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ThreadMetadata(@JsonProperty("archived") boolean archived,
        @JsonProperty("auto_archive_duration") int autoArchiveDuration,
        @JsonProperty("archive_timestamp") long archiveTimestamp,
        @JsonProperty("locked") boolean locked, @JsonProperty("invitable") boolean invitable,
        @JsonProperty("create_timestamp") String createTimestamp) {}
