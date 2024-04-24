package com.javadiscord.jdi.internal.api.impl.channel;

import java.time.OffsetDateTime;
import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

public record ListPublicArchivedThreadsRequest(
    long channelId,
    @JsonFormat(
        shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
    ) Optional<OffsetDateTime> before,
    Optional<Integer> limit
)
    implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder discordRequestBuilder = new DiscordRequestBuilder()
            .get()
            .path("/channels/%s/threads/archived/public".formatted(channelId));

        before.ifPresent(val -> discordRequestBuilder.queryParam("before", val));
        limit.ifPresent(val -> discordRequestBuilder.queryParam("limit", val));

        return discordRequestBuilder;
    }
}
