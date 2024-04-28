package com.javadiscord.jdi.core.request.builders;

import com.javadiscord.jdi.internal.api.channel.StartThreadFromMessageRequest;

import java.util.Optional;

public class StartThreadFromMessageBuilder {
    private final long channelId;
    private final long messageId;
    private String name;
    private Optional<Integer> autoArchiveDuration;
    private Optional<Integer> rateLimitPerUser;

    public StartThreadFromMessageBuilder(long channelId, long messageId) {
        this.channelId = channelId;
        this.messageId = messageId;
        this.autoArchiveDuration = Optional.empty();
        this.rateLimitPerUser = Optional.empty();
    }

    public StartThreadFromMessageBuilder autoArchiveDuration(int duration) {
        this.autoArchiveDuration = Optional.of(duration);
        return this;
    }

    public StartThreadFromMessageBuilder rateLimitPerUser(int rate) {
        this.rateLimitPerUser = Optional.of(rate);
        return this;
    }

    public StartThreadFromMessageRequest build() {
        return new StartThreadFromMessageRequest(
                channelId, messageId, name, autoArchiveDuration, rateLimitPerUser);
    }
}
