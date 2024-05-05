package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.core.models.channel.ForumAndMediaThreadMessageParams;
import com.javadiscord.jdi.internal.api.channel.StartThreadInForumOrMediaChannelRequest;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class StartThreadInForumOrMediaChannelBuilder {
    private final long channelId;
    private final String name;
    private final ForumAndMediaThreadMessageParams message;
    private Optional<Integer> autoArchiveDuration;
    private Optional<Integer> rateLimitPerUser;
    private Optional<List<Long>> appliedTags;
    private Optional<List<Path>> files;

    public StartThreadInForumOrMediaChannelBuilder(
            long channelId, String name, ForumAndMediaThreadMessageParams message) {
        this.channelId = channelId;
        this.name = name;
        this.message = message;
        this.appliedTags = Optional.empty();
        this.files = Optional.empty();
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

    public StartThreadInForumOrMediaChannelBuilder files(List<Path> files) {
        this.files = Optional.of(files);
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
                files);
    }
}
