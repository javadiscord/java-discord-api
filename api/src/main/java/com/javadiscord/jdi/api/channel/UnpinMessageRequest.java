package com.javadiscord.jdi.api.channel;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

public record UnpinMessageRequest(long channelId, long messageId) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .delete()
            .path("/channels/%s/pins/%s".formatted(channelId, messageId));
    }
}
