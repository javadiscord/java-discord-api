package com.javadiscord.jdi.internal.api.guild_scheduled_event;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.core.models.guild.EntityMetadata;
import com.javadiscord.jdi.core.models.scheduled_event.ScheduledEntityType;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

public record CreateScheduledEventRequest(
    long guildId,
    Optional<Long> channelId,
    Optional<EntityMetadata> entityMetadata,
    String name,
    int privacyLevel, // 2 is only option atm
    @JsonFormat(
        shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX"
    ) OffsetDateTime scheduledStartTime,
    @JsonFormat(
        shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssX"
    ) Optional<OffsetDateTime> scheduledEndTime,
    Optional<String> description,
    ScheduledEntityType entityType,
    Optional<String> image
) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        channelId.ifPresent(val -> body.put("channel_id", val));
        entityMetadata.ifPresent(val -> body.put("entity_metadata", val));
        body.put("name", name);
        body.put("privacy_level", privacyLevel);
        body.put(
            "scheduled_start_time",
            scheduledStartTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
        );
        scheduledEndTime.ifPresent(
            val -> body.put(
                "scheduled_end_time",
                val.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            )
        );
        description.ifPresent(val -> body.put("description", val));
        body.put("entity_type", entityType.ordinal() + 1);
        image.ifPresent(val -> body.put("image", val));
        return new DiscordRequestBuilder()
            .post()
            .path("/guilds/%s/scheduled-events".formatted(guildId))
            .body(body);
    }
}
