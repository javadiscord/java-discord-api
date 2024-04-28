package com.javadiscord.jdi.core.request.builders;

import com.javadiscord.jdi.internal.api.channel.StartThreadInForumOrMediaChannelRequest;

import java.util.List;
import java.util.Optional;

public class StartThreadInForumOrMediaChannelBuilder {
    private final long channelId;
    private final String name;
    private Optional<Integer> autoArchiveDuration;
    private Optional<Integer> rateLimitPerUser;
    private final Object message; // TODO: Create Forum and Media Thread Message Params Object
    private Optional<List<Long>> appliedTags;
    private Optional<Object> files; // TODO: Create Files object
    private Optional<String> payloadJson;

    public StartThreadInForumOrMediaChannelBuilder(long channelId, String name, Object message) {
        this.channelId = channelId;
        this.name = name;
        this.message = message;
        this.appliedTags = Optional.empty();
        this.files = Optional.empty();
        this.payloadJson = Optional.empty();
        this.autoArchiveDuration = Optional.empty();
        this.rateLimitPerUser = Optional.empty();
    }

    public StartThreadInForumOrMediaChannelBuilder autoArchiveDuration(int autoArchiveDuration) {
        this.autoArchiveDuration = Optional.of(autoArchiveDuration);
        return this;
    }

    public StartThreadInForumOrMediaChannelBuilder rateLimitPerUser(int rateLimitPerUser) {
        this.rateLimitPerUser = Optional.of(rateLimitPerUser);
        return this;
    }

    public StartThreadInForumOrMediaChannelBuilder appliedTags(List<Long> appliedTags) {
        this.appliedTags = Optional.of(appliedTags);
        return this;
    }

    public StartThreadInForumOrMediaChannelBuilder files(Object files) {
        this.files = Optional.of(files);
        return this;
    }

    public StartThreadInForumOrMediaChannelBuilder payloadJson(String payloadJson) {
        this.payloadJson = Optional.of(payloadJson);
        return this;
    }

    public StartThreadInForumOrMediaChannelRequest build() {
        return new StartThreadInForumOrMediaChannelRequest(
                channelId,
                name,
                autoArchiveDuration,
                rateLimitPerUser,
                message,
                appliedTags,
                files,
                payloadJson);
    }
}
