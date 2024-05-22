package com.javadiscord.jdi.internal.api.channel;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record ListThreadMembersRequest(
    long channelId,
    Optional<Boolean> withMember,
    Optional<Long> after,
    Optional<Integer> limit
) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder discordRequestBuilder
            = new DiscordRequestBuilder()
                .get()
                .path("/channels/%s/thread-members".formatted(channelId));

        withMember.ifPresent(val -> discordRequestBuilder.queryParam("with_member", val));
        after.ifPresent(val -> discordRequestBuilder.queryParam("after", val));
        limit.ifPresent(val -> discordRequestBuilder.queryParam("limit", val));

        return discordRequestBuilder;
    }
}
