package com.javadiscord.jdi.internal.api.impl.channel;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.Optional;

public record ListJoinedPrivateArchivedThreadsRequest(
        long channelId,
        Optional<Long> before,
        Optional<Integer> limit) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder discordRequestBuilder =
                new DiscordRequestBuilder()
                        .get()
                        .path("/channels/%s/users/@me/threads/archived/private".formatted(channelId));

        before.ifPresent(val -> discordRequestBuilder.queryParam("before", val));
        limit.ifPresent(val -> discordRequestBuilder.queryParam("limit", val));

        return discordRequestBuilder;
    }
}
