package com.javadiscord.jdi.internal.api.channel;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record FetchUserReactionsToMessageRequest(
    long channelId, long messageId, String emoji, Optional<Long> after, Optional<Integer> limit
)
    implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder discordRequestBuilder = new DiscordRequestBuilder()
            .get()
            .path(
                "/channels/%s/messages/%s/reactions/%s"
                    .formatted(channelId, messageId, emoji)
            );
        after.ifPresent(val -> discordRequestBuilder.queryParam("after", val));
        limit.ifPresent(val -> discordRequestBuilder.queryParam("limit", val));
        return discordRequestBuilder;
    }
}
