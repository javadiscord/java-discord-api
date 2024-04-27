package com.javadiscord.jdi.api.poll;

import java.util.Optional;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

public record GetAnswerVotersRequest(
    long channelId,
    long messageId,
    long answerId,
    Optional<Long> after,
    Optional<Integer> limit
) implements DiscordRequest {

    public GetAnswerVotersRequest {
        if (limit.isPresent() && (limit.get() > 100 || limit.get() < 0)) {
            throw new IllegalArgumentException("limit must be between 1-100");
        }
    }

    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder builder = new DiscordRequestBuilder()
            .path("/channels/%s/polls/%s/answers/%s".formatted(channelId, messageId, answerId))
            .get();

        after.ifPresent(val -> builder.queryParam("after", val));
        limit.ifPresent(val -> builder.queryParam("limit", val));

        return builder;
    }
}
