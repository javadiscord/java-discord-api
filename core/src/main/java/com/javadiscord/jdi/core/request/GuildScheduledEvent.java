package com.javadiscord.jdi.core.request;

import com.javadiscord.jdi.core.request.builders.CreateScheduledEventBuilder;
import com.javadiscord.jdi.core.request.builders.GetScheduledEventBuilder;
import com.javadiscord.jdi.core.request.builders.GetScheduledEventUsersBuilder;
import com.javadiscord.jdi.core.request.builders.ListScheduledEventsBuilder;
import com.javadiscord.jdi.internal.api.guild_scheduled_event.*;

public class GuildScheduledEvent {

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
