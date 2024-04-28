package com.javadiscord.jdi.core.request.builders;

import com.javadiscord.jdi.internal.api.channel.FetchChannelMessagesRequest;

import java.util.Optional;

public class FetchChannelMessagesBuilder {
    private final long channelId;
    private Optional<Long> around;
    private Optional<Long> before;
    private Optional<Long> after;
    private final int limit;

    public FetchChannelMessagesBuilder(long channelId, int limit) {
        this.channelId = channelId;
        this.limit = limit;
        around = Optional.empty();
        before = Optional.empty();
        after = Optional.empty();
    }

    public FetchChannelMessagesBuilder around(long around) {
        this.around = Optional.of(around);
        return this;
    }

    public FetchChannelMessagesBuilder before(long before) {
        this.before = Optional.of(before);
        return this;
    }

    public FetchChannelMessagesBuilder after(long after) {
        this.after = Optional.of(after);
        return this;
    }

    public FetchChannelMessagesRequest build() {
        return new FetchChannelMessagesRequest(channelId, around, before, after, limit);
    }
}
