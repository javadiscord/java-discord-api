package com.javadiscord.jdi.core.models.channel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ThreadMetadata(
    @JsonProperty("archived") boolean archived,
    @JsonProperty("auto_archive_duration") int autoArchiveDuration,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX") @JsonProperty(
        "archive_timestamp"
    ) String archiveTimestamp,
    @JsonProperty("locked") boolean locked,
    @JsonProperty("invitable") boolean invitable,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX") @JsonProperty(
        "create_timestamp"
    ) String createTimestamp
) {}
