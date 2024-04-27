package com.javadiscord.jdi.api.channel;

import java.util.Optional;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

public record EditChannelPermissionsRequest(
    long channelId, long overwriteId, Optional<String> allow, Optional<String> deny, int type
)
    implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .put()
            .path("/channels/%s/permissions/%s".formatted(channelId, overwriteId));
    }
}
