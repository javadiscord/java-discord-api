package com.javadiscord.jdi.internal.api.stage;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record ModifyStageRequest(
        String channelId, Optional<String> topic, Optional<Integer> privacyLevel)
        implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        topic.ifPresent(val -> body.put("topic", val));
        privacyLevel.ifPresent(val -> body.put("privacy_level", privacyLevel));
        return new DiscordRequestBuilder()
                .patch()
                .path("/stage-instances/%s".formatted(channelId))
                .body(body);
    }
}
