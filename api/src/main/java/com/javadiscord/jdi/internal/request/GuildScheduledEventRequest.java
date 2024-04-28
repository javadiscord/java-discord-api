package com.javadiscord.jdi.internal.request;

import com.javadiscord.jdi.internal.api.guild_scheduled_event.*;
import com.javadiscord.jdi.internal.request.builders.CreateScheduledEventBuilder;
import com.javadiscord.jdi.internal.request.builders.GetScheduledEventBuilder;
import com.javadiscord.jdi.internal.request.builders.GetScheduledEventUsersBuilder;
import com.javadiscord.jdi.internal.request.builders.ListScheduledEventsBuilder;
import com.javadiscord.jdi.internal.response.DiscordResponseParser;

public class GuildScheduledEventRequest {
    private final DiscordResponseParser responseParser;

    public GuildScheduledEventRequest(DiscordResponseParser responseParser) {
        this.responseParser = responseParser;
    }

    public CreateScheduledEventRequest createScheduledEvent(CreateScheduledEventBuilder builder) {
        return builder.build();
    }

    public DeleteScheduledEventRequest deleteScheduledEvent(long guildId, long scheduledEventId) {
        return new DeleteScheduledEventRequest(guildId, scheduledEventId);
    }

    public GetScheduledEventRequest getScheduledEvent(GetScheduledEventBuilder builder) {
        return builder.build();
    }

    public GetScheduledEventUsersRequest getScheduledEventUsers(
            GetScheduledEventUsersBuilder builder) {
        return builder.build();
    }

    public ListScheduledEventsRequest listScheduledEvents(ListScheduledEventsBuilder builder) {
        return builder.build();
    }
}
