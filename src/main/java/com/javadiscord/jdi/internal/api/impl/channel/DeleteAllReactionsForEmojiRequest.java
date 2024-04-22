package com.javadiscord.jdi.internal.api.impl.channel;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record DeleteAllReactionsForEmojiRequest(long channelId, long messageId, String emoji)
    implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .delete()
            .path(
                "/channels/%s/messages/%s/reactions/%s"
                    .formatted(channelId, messageId, emoji)
            );
    }
}
