package com.javadiscord.jdi.internal.api.guild;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record ModifyCurrentUserVoiceStateRequest(
        long guildId,
        Optional<Long> channelId,
        Optional<Boolean> suppress,
        Optional<OffsetDateTime> requestToSpeakTimestamp)
        implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        channelId.ifPresent(val -> body.put("channel_id", val));
        suppress.ifPresent(val -> body.put("suppress", val));
        requestToSpeakTimestamp.ifPresent(val -> body.put("request_to_speak_timestamp", val));

        return new DiscordRequestBuilder()
                .patch()
                .path("/guilds/%s/voice-states/@me".formatted(guildId))
                .body(body);
    }
}
