package com.javadiscord.jdi.internal.api.impl.guild;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record ModifyUserVoiceStateRequest(
        long guildId, long userId, Optional<Long> channelId, Optional<Boolean> suppress)
        implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        channelId.ifPresent(val -> body.put("channel_id", val));
        suppress.ifPresent(val -> body.put("suppress", val));

        return new DiscordRequestBuilder()
                .patch()
                .path("/guilds/%s/voice-states/%s".formatted(guildId, userId))
                .body(body);
    }
}
