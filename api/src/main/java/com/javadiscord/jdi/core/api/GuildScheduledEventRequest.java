package com.javadiscord.jdi.core.api;

import com.javadiscord.jdi.core.api.builders.CreateScheduledEventBuilder;
import com.javadiscord.jdi.core.api.builders.GetScheduledEventBuilder;
import com.javadiscord.jdi.core.api.builders.GetScheduledEventUsersBuilder;
import com.javadiscord.jdi.core.api.builders.ListScheduledEventsBuilder;
import com.javadiscord.jdi.core.models.scheduled_event.EventUser;
import com.javadiscord.jdi.core.models.scheduled_event.ScheduledEvent;
import com.javadiscord.jdi.internal.api.guild_scheduled_event.*;

import java.util.List;

public class GuildScheduledEventRequest {
    private final DiscordResponseParser responseParser;
    private final long guildId;

    public GuildScheduledEventRequest(DiscordResponseParser responseParser, long guildId) {
        this.responseParser = responseParser;
        this.guildId = guildId;
    }

    public AsyncResponse<ScheduledEvent> createScheduledEvent(CreateScheduledEventBuilder builder) {
        return responseParser.callAndParse(
                ScheduledEvent.class, builder.setGuildId(guildId).build());
    }

    public AsyncResponse<ScheduledEvent> deleteScheduledEvent(long scheduledEventId) {
        return responseParser.callAndParse(
                ScheduledEvent.class, new DeleteScheduledEventRequest(guildId, scheduledEventId));
    }

    public AsyncResponse<ScheduledEvent> getScheduledEvent(GetScheduledEventBuilder builder) {
        return responseParser.callAndParse(
                ScheduledEvent.class, builder.setGuildId(guildId).build());
    }

    public AsyncResponse<List<EventUser>> getScheduledEventUsers(
            GetScheduledEventUsersBuilder builder) {
        return responseParser.callAndParseList(
                EventUser.class, builder.setGuildId(guildId).build());
    }

    public AsyncResponse<List<ScheduledEvent>> listScheduledEvents(
            ListScheduledEventsBuilder builder) {
        return responseParser.callAndParseList(
                ScheduledEvent.class, builder.setGuildId(guildId).build());
    }
}
