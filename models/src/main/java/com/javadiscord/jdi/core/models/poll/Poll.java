package com.javadiscord.jdi.core.models.poll;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Poll(
        @JsonProperty("question") PollMedia question,
        @JsonProperty("answers") List<PollAnswer> answers,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX")
                @JsonProperty("expiry")
                OffsetDateTime expiry,
        @JsonProperty("allow_multiselect") boolean allowMultiSelect,
        @JsonProperty("layout_type") PollLayoutType layoutType,
        @JsonProperty("results") PollResults results) {}
