package com.javadiscord.jdi.core.models.poll;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PollAnswer(
    @JsonProperty("answer_id") int answerId,
    @JsonProperty("poll_media") PollMedia pollMedia
) {}
