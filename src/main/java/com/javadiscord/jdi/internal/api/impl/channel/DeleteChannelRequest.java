package com.javadiscord.jdi.internal.api.impl.channel;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record DeleteChannelRequest(long channelId, long overwriteId) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .delete()
            .path("/channels/%s/permissions/%s".formatted(channelId, overwriteId));
    }
}
