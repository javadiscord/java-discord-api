package com.javadiscord.jdi.internal.request;

import com.javadiscord.jdi.core.models.scheduled_event.EventUser;
import com.javadiscord.jdi.core.models.scheduled_event.ScheduledEvent;
import com.javadiscord.jdi.internal.api.guild_scheduled_event.*;
import com.javadiscord.jdi.internal.request.builders.CreateScheduledEventBuilder;
import com.javadiscord.jdi.internal.request.builders.GetScheduledEventBuilder;
import com.javadiscord.jdi.internal.request.builders.GetScheduledEventUsersBuilder;
import com.javadiscord.jdi.internal.request.builders.ListScheduledEventsBuilder;
import com.javadiscord.jdi.internal.response.AsyncResponse;
import com.javadiscord.jdi.internal.response.DiscordResponseParser;

import java.util.List;

public class GuildScheduledEventRequest {
    private final DiscordResponseParser responseParser;

    public GuildScheduledEventRequest(DiscordResponseParser responseParser) {
        this.responseParser = responseParser;
    }

    public AsyncResponse<ScheduledEvent> createScheduledEvent(CreateScheduledEventBuilder builder) {
        return responseParser.callAndParse(ScheduledEvent.class, builder.build());
    }

    public AsyncResponse<ScheduledEvent> deleteScheduledEvent(long guildId, long scheduledEventId) {
        return responseParser.callAndParse(
                ScheduledEvent.class, new DeleteScheduledEventRequest(guildId, scheduledEventId));
    }

    public AsyncResponse<ScheduledEvent> getScheduledEvent(GetScheduledEventBuilder builder) {
        return responseParser.callAndParse(ScheduledEvent.class, builder.build());
    }

    public AsyncResponse<List<EventUser>> getScheduledEventUsers(
            GetScheduledEventUsersBuilder builder) {
        return responseParser.callAndParseList(EventUser.class, builder.build());
    }

    public AsyncResponse<List<ScheduledEvent>> listScheduledEvents(
            ListScheduledEventsBuilder builder) {
        return responseParser.callAndParseList(ScheduledEvent.class, builder.build());
    }
}
