package com.javadiscord.jdi.internal.models.invite;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.OffsetDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record InviteMetadata(
        @JsonProperty("uses") int uses,
        @JsonProperty("max_uses") int maxUses,
        @JsonProperty("max_age") int maxAge,
        @JsonProperty("temporary") boolean temporary,
        @JsonFormat(
                shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
        )
        @JsonProperty("created_at") OffsetDateTime createdAt
        ) {
}