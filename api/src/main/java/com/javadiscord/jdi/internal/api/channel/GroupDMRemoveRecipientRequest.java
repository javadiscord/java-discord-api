package com.javadiscord.jdi.internal.api.channel;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record GroupDMRemoveRecipientRequest(long channelId, long userId) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .delete()
                .path("/channels/%s/recipients/%s".formatted(channelId, userId));
    }
}
