package com.javadiscord.jdi.internal.api.impl.channel;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record CreateReactionRequest(long channelId, long messageId, String emoji)
    implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .put()
            .path(
                "/channels/%s/messages/%s/reactions/%s/@me"
                    .formatted(channelId, messageId, emoji)
            );
    }
}
