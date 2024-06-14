package com.javadiscord.jdi.internal.api.guild_scheduled_event;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.core.models.guild.EntityMetadata;
import com.javadiscord.jdi.core.models.scheduled_event.ScheduledEntityType;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record ModifyScheduledEventRequest(
    long guildId,
    long scheduledEventId,
    Optional<Long> channelId,
    Optional<EntityMetadata> entityMetadata,
    Optional<String> name,
    Optional<Integer> privacyLevel,
    Optional<Long> scheduledStartTime,
    Optional<Long> scheduledEndTime,
    Optional<String> description,
    Optional<ScheduledEntityType> entityType,
    Optional<Integer> status,
    Optional<String> image
) implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        channelId.ifPresent(val -> body.put("channel_id", val));
        entityMetadata.ifPresent(val -> body.put("entity_metadata", val));
        name.ifPresent(val -> body.put("name", val));
        privacyLevel.ifPresent(val -> body.put("privacy_level", val));
        scheduledStartTime.ifPresent(val -> body.put("scheduled_start_time", val));
        scheduledEndTime.ifPresent(val -> body.put("scheduled_end_time", val));
        description.ifPresent(val -> body.put("description", val));
        entityType.ifPresent(val -> body.put("entity_time", val));
        status.ifPresent(val -> body.put("status", val));
        image.ifPresent(val -> body.put("image", val));
        return new DiscordRequestBuilder()
            .patch()
            .path("/guilds/%s/scheduled-events/%s".formatted(guildId, scheduledEventId))
            .body(body);
    }
}
