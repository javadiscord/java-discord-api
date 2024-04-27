package com.javadiscord.jdi.internal.models.poll;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PollResults(
    @JsonProperty("is_finalized") boolean isFinalized,
    @JsonProperty("answer_counts") List<PollAnswerCount> answerCounts
) {}
