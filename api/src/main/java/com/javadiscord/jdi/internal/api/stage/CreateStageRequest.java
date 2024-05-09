package com.javadiscord.jdi.internal.api.stage;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record CreateStageRequest(
        long channelId,
        String topic,
        int privacyLevel,
        Optional<Boolean> sendStartNotification,
        Optional<Long> guildScheduledEventId,
        Optional<String> auditReason)
        implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        body.put("channel_id", channelId);
        body.put("topic", topic);
        body.put("privacy_level", privacyLevel);
        sendStartNotification.ifPresent(val -> body.put("send_start_notification", val));
        guildScheduledEventId.ifPresent(val -> body.put("guild_scheduled_event_id", val));

        DiscordRequestBuilder builder = new DiscordRequestBuilder();
        builder.post();
        builder.path("/stage-instances");
        builder.body(body);

        auditReason.ifPresent(val -> builder.putHeader("audit_reason", val));

        return builder;
    }
}
