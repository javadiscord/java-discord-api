package com.javadiscord.jdi.api.channel;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

public record GroupDMAddRecipientRequest(
    long channelId, long userId, String accessToken, String nickname
)
    implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .put()
            .path("/channels/%s/recipients/%s".formatted(channelId, userId));
    }
}
