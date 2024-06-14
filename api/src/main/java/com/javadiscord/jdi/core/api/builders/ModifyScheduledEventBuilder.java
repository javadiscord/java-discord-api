package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.core.models.guild.EntityMetadata;
import com.javadiscord.jdi.core.models.guild.PrivacyLevel;
import com.javadiscord.jdi.core.models.scheduled_event.ScheduledEntityType;
import com.javadiscord.jdi.internal.api.guild_scheduled_event.ModifyScheduledEventRequest;

public class ModifyScheduledEventBuilder {
    private final long guildId;
    private final long scheduledEventId;
    private Optional<Long> channelId;
    private Optional<EntityMetadata> entityMetadata;
    private Optional<String> name;
    private Optional<Integer> privacyLevel;
    private Optional<Long> scheduledStartTime;
    private Optional<Long> scheduledEndTime;
    private Optional<String> description;
    private Optional<ScheduledEntityType> entityType;
    private Optional<Integer> status;
    private Optional<String> image;

    public ModifyScheduledEventBuilder(long guildId, long scheduledEventId) {
        this.guildId = guildId;
        this.scheduledEventId = scheduledEventId;
        this.channelId = Optional.empty();
        this.entityMetadata = Optional.empty();
        this.name = Optional.empty();
        this.privacyLevel = Optional.empty();
        this.scheduledStartTime = Optional.empty();
        this.scheduledEndTime = Optional.empty();
        this.description = Optional.empty();
        this.entityType = Optional.empty();
        this.status = Optional.empty();
        this.image = Optional.empty();
    }

    public ModifyScheduledEventBuilder channelId(long channelId) {
        this.channelId = Optional.of(channelId);
        return this;
    }

    public ModifyScheduledEventBuilder entityMetadata(EntityMetadata entityMetadata) {
        this.entityMetadata = Optional.of(entityMetadata);
        return this;
    }

    public ModifyScheduledEventBuilder name(String name) {
        this.name = Optional.of(name);
        return this;
    }

    public ModifyScheduledEventBuilder privacyLevel(PrivacyLevel privacyLevel) {
        this.privacyLevel = Optional.of(privacyLevel.ordinal());
        return this;
    }

    public ModifyScheduledEventBuilder scheduledStartTime(long scheduledStartTime) {
        this.scheduledStartTime = Optional.of(scheduledStartTime);
        return this;
    }

    public ModifyScheduledEventBuilder scheduledEndTime(long scheduledEndTim) {
        this.scheduledEndTime = Optional.of(scheduledEndTim);
        return this;
    }

    public ModifyScheduledEventBuilder description(String description) {
        this.description = Optional.of(description);
        return this;
    }

    public ModifyScheduledEventBuilder entityType(ScheduledEntityType entityType) {
        this.entityType = Optional.of(entityType);
        return this;
    }

    public ModifyScheduledEventBuilder status(int status) {
        this.status = Optional.of(status);
        return this;
    }

    public ModifyScheduledEventBuilder image(String image) {
        this.image = Optional.of(image);
        return this;
    }

    public ModifyScheduledEventRequest build() {
        if (entityType.isPresent() && entityType.get() == ScheduledEntityType.EXTERNAL) {
            if (entityMetadata.isEmpty() || scheduledEndTime.isEmpty()) {
                throw new IllegalArgumentException(
                    "When entityType is EXTERNAL, both entityMetadata and scheduledEndTime must"
                        + " be provided"
                );
            }
            if (channelId.isPresent()) {
                throw new IllegalArgumentException(
                    "When entityType is EXTERNAL, channelId must not be provided"
                );
            }
        }

        return new ModifyScheduledEventRequest(
            guildId,
            scheduledEventId,
            channelId,
            entityMetadata,
            name,
            privacyLevel,
            scheduledStartTime,
            scheduledEndTime,
            description,
            entityType,
            status,
            image
        );
    }
}
