package com.javadiscord.jdi.internal.api.guild;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record ModifyGuildWidgetRequest(
    long guildId, Optional<Boolean> enabled, Optional<Long> channelId
)
    implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        enabled.ifPresent(val -> body.put("enabled", val));
        channelId.ifPresent(val -> body.put("channel_id", val));

        return new DiscordRequestBuilder()
            .patch()
            .path("/guilds/%s/widget".formatted(guildId))
            .body(body);
    }
}
