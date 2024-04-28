package com.javadiscord.jdi.internal.request.builders;

import com.javadiscord.jdi.internal.api.channel.StartThreadWithoutMessageRequest;

import java.util.Optional;

public class StartThreadWithoutMessageBuilder {
    private final long channelId;
    private final String name;
    private Optional<Integer> autoArchiveDuration;
    private Optional<Integer> type;
    private Optional<Boolean> invitable;
    private Optional<Integer> rateLimitPerUser;

    public StartThreadWithoutMessageBuilder(long channelId, String name) {
        this.channelId = channelId;
        this.name = name;
        this.autoArchiveDuration = Optional.empty();
        this.type = Optional.empty();
        this.invitable = Optional.empty();
        this.rateLimitPerUser = Optional.empty();
    }

    public StartThreadWithoutMessageBuilder autoArchiveDuration(int duration) {
        this.autoArchiveDuration = Optional.of(duration);
        return this;
    }

    public StartThreadWithoutMessageBuilder type(int type) {
        this.type = Optional.of(type);
        return this;
    }

    public StartThreadWithoutMessageBuilder invitable(boolean invitable) {
        this.invitable = Optional.of(invitable);
        return this;
    }

    public StartThreadWithoutMessageBuilder rateLimitPerUser(int rateLimitPerUser) {
        this.rateLimitPerUser = Optional.of(rateLimitPerUser);
        return this;
    }

    public StartThreadWithoutMessageRequest build() {
        return new StartThreadWithoutMessageRequest(
                channelId, name, autoArchiveDuration, type, invitable, rateLimitPerUser);
    }
}
