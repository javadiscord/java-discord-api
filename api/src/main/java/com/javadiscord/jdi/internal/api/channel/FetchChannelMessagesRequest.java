package com.javadiscord.jdi.internal.api.channel;

import java.util.Optional;
import java.util.stream.Stream;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record FetchChannelMessagesRequest(
    long channelId,
    Optional<Long> around,
    Optional<Long> before,
    Optional<Long> after,
    int limit
) implements DiscordRequest {

    public FetchChannelMessagesRequest {
        if (Stream.of(around, before, after).filter(Optional::isPresent).count() > 1) {
            throw new IllegalArgumentException(
                "Only one of the values 'around', 'before', or 'after' should be set."
            );
        }
    }

    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder requestBuilder = new DiscordRequestBuilder();
        requestBuilder.get();

        around.ifPresent(val -> requestBuilder.queryParam("around", val));
        before.ifPresent(val -> requestBuilder.queryParam("before", val));
        after.ifPresent(val -> requestBuilder.queryParam("after", val));

        requestBuilder.queryParam("limit", limit);
        requestBuilder.path("/channels/%s/messages".formatted(channelId));
        return requestBuilder;
    }
}
