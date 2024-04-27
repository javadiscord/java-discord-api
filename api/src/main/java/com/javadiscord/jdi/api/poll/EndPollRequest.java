package com.javadiscord.jdi.api.poll;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

public record EndPollRequest(long channelId, long messageId) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .path("/channels/%s/polls/%s/expire".formatted(channelId, messageId))
            .post();
    }
}
