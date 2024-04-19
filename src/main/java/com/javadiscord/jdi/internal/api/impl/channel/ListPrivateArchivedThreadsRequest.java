package com.javadiscord.jdi.internal.api.impl.channel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.time.OffsetDateTime;
import java.util.Optional;

public record ListPrivateArchivedThreadsRequest(
        long channelId,
        @JsonFormat(
                shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
        ) Optional<OffsetDateTime> before,
        Optional<Integer> limit) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder discordRequestBuilder =
                new DiscordRequestBuilder()
                        .get()
                        .path("/channels/%s/threads/archived/private".formatted(channelId));

        before.ifPresent(val -> discordRequestBuilder.queryParam("before", val));
        limit.ifPresent(val -> discordRequestBuilder.queryParam("limit", val));

        return discordRequestBuilder;
    }
}
