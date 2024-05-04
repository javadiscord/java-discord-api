package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.core.models.channel.*;
import com.javadiscord.jdi.core.models.guild.Tags;
import com.javadiscord.jdi.internal.api.guild.CreateGuildChannelRequest;

import java.util.List;
import java.util.Optional;

public class CreateGuildChannelBuilder {
    private long guildId;
    private final String name;
    private Optional<ChannelType> type;
    private Optional<String> topic;
    private Optional<Integer> bitrate;
    private Optional<Integer> userLimit;
    private Optional<Integer> rateLimitPerUser;
    private Optional<Integer> position;
    private Optional<List<Overwrite>> permissionOverwrites;
    private Optional<Long> parentId;
    private Optional<Boolean> nsfw;
    private Optional<String> rtcRegion;
    private Optional<VideoQualityMode> videoQualityMode;
    private Optional<Integer> defaultAutoArchiveDuration;
    private Optional<DefaultReaction> defaultReactionEmoji;
    private Optional<List<Tags>> availableTags;
    private Optional<SortOrderType> defaultSortOrder;
    private Optional<ForumLayoutType> defaultForumLayout;
    private Optional<Integer> defaultThreadRateLimitPerUser;

    public CreateGuildChannelBuilder(String name) {
        this.name = name;
        this.type = Optional.empty();
        this.topic = Optional.empty();
        this.bitrate = Optional.empty();
        this.userLimit = Optional.empty();
        this.rateLimitPerUser = Optional.empty();
        this.position = Optional.empty();
        this.permissionOverwrites = Optional.empty();
        this.parentId = Optional.empty();
        this.nsfw = Optional.empty();
        this.rtcRegion = Optional.empty();
        this.videoQualityMode = Optional.empty();
        this.defaultAutoArchiveDuration = Optional.empty();
        this.defaultReactionEmoji = Optional.empty();
        this.availableTags = Optional.empty();
        this.defaultSortOrder = Optional.empty();
        this.defaultForumLayout = Optional.empty();
        this.defaultThreadRateLimitPerUser = Optional.empty();
    }

    public CreateGuildChannelRequest build() {
        return new CreateGuildChannelRequest(
                guildId,
                name,
                type,
                topic,
                bitrate,
                userLimit,
                rateLimitPerUser,
                position,
                permissionOverwrites,
                parentId,
                nsfw,
                rtcRegion,
                videoQualityMode,
                defaultAutoArchiveDuration,
                defaultReactionEmoji,
                availableTags,
                defaultSortOrder,
                defaultForumLayout,
                defaultThreadRateLimitPerUser);
    }

    public CreateGuildChannelBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public CreateGuildChannelBuilder type(ChannelType type) {
        this.type = Optional.of(type);
        return this;
    }

    public CreateGuildChannelBuilder topic(String topic) {
        this.topic = Optional.of(topic);
        return this;
    }

    public CreateGuildChannelBuilder bitrate(int bitrate) {
        this.bitrate = Optional.of(bitrate);
        return this;
    }

    public CreateGuildChannelBuilder userLimit(int userLimit) {
        this.userLimit = Optional.of(userLimit);
        return this;
    }

    public CreateGuildChannelBuilder rateLimitPerUser(int rateLimitPerUser) {
        this.rateLimitPerUser = Optional.of(rateLimitPerUser);
        return this;
    }

    public CreateGuildChannelBuilder position(int position) {
        this.position = Optional.of(position);
        return this;
    }

    public CreateGuildChannelBuilder permissionOverwrites(List<Overwrite> permissionOverwrites) {
        this.permissionOverwrites = Optional.of(permissionOverwrites);
        return this;
    }

    public CreateGuildChannelBuilder parentId(long parentId) {
        this.parentId = Optional.of(parentId);
        return this;
    }

    public CreateGuildChannelBuilder nsfw(boolean nsfw) {
        this.nsfw = Optional.of(nsfw);
        return this;
    }

    public CreateGuildChannelBuilder rtcRegion(String rtcRegion) {
        this.rtcRegion = Optional.of(rtcRegion);
        return this;
    }

    public CreateGuildChannelBuilder videoQualityMode(VideoQualityMode videoQualityMode) {
        this.videoQualityMode = Optional.of(videoQualityMode);
        return this;
    }

    public CreateGuildChannelBuilder defaultAutoArchiveDuration(int defaultAutoArchiveDuration) {
        this.defaultAutoArchiveDuration = Optional.of(defaultAutoArchiveDuration);
        return this;
    }

    public CreateGuildChannelBuilder defaultReactionEmoji(DefaultReaction defaultReactionEmoji) {
        this.defaultReactionEmoji = Optional.of(defaultReactionEmoji);
        return this;
    }

    public CreateGuildChannelBuilder availableTags(List<Tags> availableTags) {
        this.availableTags = Optional.of(availableTags);
        return this;
    }

    public CreateGuildChannelBuilder defaultSortOrder(SortOrderType defaultSortOrder) {
        this.defaultSortOrder = Optional.of(defaultSortOrder);
        return this;
    }

    public CreateGuildChannelBuilder defaultForumLayout(ForumLayoutType defaultForumLayout) {
        this.defaultForumLayout = Optional.of(defaultForumLayout);
        return this;
    }

    public CreateGuildChannelBuilder defaultThreadRateLimitPerUser(
            int defaultThreadRateLimitPerUser) {
        this.defaultThreadRateLimitPerUser = Optional.of(defaultThreadRateLimitPerUser);
        return this;
    }
}
