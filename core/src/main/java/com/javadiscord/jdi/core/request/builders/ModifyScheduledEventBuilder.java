package com.javadiscord.jdi.core.request.builders;

import com.javadiscord.jdi.core.models.guild.EntityMetadata;
import com.javadiscord.jdi.core.models.guild.PrivacyLevel;
import com.javadiscord.jdi.internal.api.guild_scheduled_event.ModifyScheduledEventRequest;

import java.util.Optional;

public class ModifyScheduledEventBuilder {
    private final long guildId;
    private final long scheduledEventId;
    private Optional<Long> channelId;
    private Optional<EntityMetadata> entityMetadata;
    private Optional<String> name;
    private Optional<Integer> privacyLevel;
    private Optional<Long> scheduledStartTime;
    private Optional<Long> scheduledEndTim;
    private Optional<String> description;
    private Optional<Integer> entityType;
    private Optional<Integer> status;
    private Optional<String> image;

    public ModifyScheduledEventBuilder(long guildId, long scheduledEventId) {
        this.guildId = guildId;
        this.scheduledEventId = scheduledEventId;
        channelId = Optional.empty();
        entityMetadata = Optional.empty();
        name = Optional.empty();
        privacyLevel = Optional.empty();
        scheduledStartTime = Optional.empty();
        scheduledEndTim = Optional.empty();
        description = Optional.empty();
        entityType = Optional.empty();
        status = Optional.empty();
        image = Optional.empty();
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

    public ModifyScheduledEventBuilder scheduledEndTim(long scheduledEndTim) {
        this.scheduledEndTim = Optional.of(scheduledEndTim);
        return this;
    }

    public ModifyScheduledEventBuilder description(String description) {
        this.description = Optional.of(description);
        return this;
    }

    public ModifyScheduledEventBuilder entityType(Integer entityType) {
        this.entityType = Optional.of(entityType);
        return this;
    }

    public ModifyScheduledEventBuilder status(Integer status) {
        this.status = Optional.of(status);
        return this;
    }

    public ModifyScheduledEventBuilder image(String image) {
        this.image = Optional.of(image);
        return this;
    }

    public ModifyScheduledEventRequest build() {
        return new ModifyScheduledEventRequest(guildId, scheduledEventId, channelId, entityMetadata, name, privacyLevel, scheduledStartTime, scheduledEndTim, description, entityType, status, image);
    }
}
