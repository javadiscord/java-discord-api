package com.javadiscord.jdi.internal.api.impl.channel;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record DeleteUserReactionRequest(long channelId, long messageId, String emoji, long userId)
        implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .delete()
                .path(
                        "/channels/%s/messages/%s/reactions/%s}/%s"
                                .formatted(channelId, messageId, emoji, userId));
    }
}
