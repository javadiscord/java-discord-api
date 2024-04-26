package com.javadiscord.jdi.internal.api.stage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record CreateStageRequest(
    long channelId,
    String topic,
    int privacyLevel,
    Optional<Boolean> sendStartNotification,
    Optional<Long> guildScheduledEventId
)
    implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        // TODO: X-Audit-Log-Reason header
        Map<String, Object> body = new HashMap<>();
        body.put("channel_id", channelId);
        body.put("topic", topic);
        body.put("privacy_level", privacyLevel);
        sendStartNotification.ifPresent(val -> body.put("send_start_notification", val));
        guildScheduledEventId.ifPresent(val -> body.put("guild_scheduled_event_id", val));

        return new DiscordRequestBuilder().post().path("/stage-instances").body(body);
    }
}
