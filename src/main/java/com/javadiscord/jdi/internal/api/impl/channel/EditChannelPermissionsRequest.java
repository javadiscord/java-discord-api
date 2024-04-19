package com.javadiscord.jdi.internal.api.impl.channel;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.Optional;

public record EditChannelPermissionsRequest(
        long channelId,
        long overwriteId,
        Optional<String> allow,
        Optional<String> deny,
        int type) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .put()
                .path("/channels/%s/permissions/%s".formatted(channelId, overwriteId));
    }
}
