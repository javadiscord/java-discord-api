package com.javadiscord.jdi.internal.models.poll;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PollAnswerCount(
        @JsonProperty("id") int id,
        @JsonProperty("count") int count,
        @JsonProperty("me_voted") boolean meVoted) {}
