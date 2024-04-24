package com.javadiscord.jdi.internal.api.impl.guild;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;
import com.javadiscord.jdi.internal.models.channel.ChannelType;
import com.javadiscord.jdi.internal.models.channel.DefaultReaction;
import com.javadiscord.jdi.internal.models.channel.ForumLayoutType;
import com.javadiscord.jdi.internal.models.channel.Overwrite;
import com.javadiscord.jdi.internal.models.guild.SortOrderType;
import com.javadiscord.jdi.internal.models.guild.Tags;
import com.javadiscord.jdi.internal.models.guild.VideoQualityMode;

public record CreateGuildChannelRequest(
    long guildId,
    String name,
    Optional<ChannelType> type,
    Optional<String> topic,
    Optional<Integer> bitrate,
    Optional<Integer> userLimit,
    Optional<Integer> rateLimitPerUser,
    Optional<Integer> position,
    Optional<List<Overwrite>> permissionOverwrites,
    Optional<Long> parentId,
    Optional<Boolean> nsfw,
    Optional<String> rtcRegion,
    Optional<VideoQualityMode> videoQualityMode,
    Optional<Integer> defaultAutoArchiveDuration,
    Optional<DefaultReaction> defaultReactionEmoji,
    Optional<List<Tags>> availableTags,
    Optional<SortOrderType> defaultSortOrder,
    Optional<ForumLayoutType> defaultForumLayout,
    Optional<Integer> defaultThreadRateLimitPerUser
)
    implements DiscordRequest {

    public CreateGuildChannelRequest {
        if (name.isEmpty() || name.length() > 100) {
            throw new IllegalArgumentException(
                "Channel name must be between 1 and 100 character's long"
            );
        }

        if (topic.isPresent()) {
            String value = topic.get();
            if (value.length() > 1024) {
                throw new IllegalArgumentException(
                    "Channel topic must be between 0 and 1024 characters"
                );
            }
        }

        if (rateLimitPerUser.isPresent()) {
            int value = rateLimitPerUser.get();
            if (value < 0 || value > 21600) {
                throw new IllegalArgumentException(
                    "Rate limit per user must be between 0 and 21,600"
                );
            }
        }

        if (bitrate.isPresent()) {
            int value = bitrate.get();
            if (value < 8000) {
                throw new IllegalArgumentException("Bitrate must be at least 8,000");
            }
        }
    }

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        body.put("name", name);
        type.ifPresent(val -> body.put("type", val));
        topic.ifPresent(val -> body.put("topic", val));
        bitrate.ifPresent(val -> body.put("bitrate", val));
        userLimit.ifPresent(val -> body.put("user_limit", val));
        rateLimitPerUser.ifPresent(val -> body.put("rate_limit_per_user", val));
        position.ifPresent(val -> body.put("position", val));
        permissionOverwrites.ifPresent(val -> body.put("permission_overwrites", val));
        parentId.ifPresent(val -> body.put("parent_id", val));
        nsfw.ifPresent(val -> body.put("nsfw", val));
        rtcRegion.ifPresent(val -> body.put("rtc_region", val));
        videoQualityMode.ifPresent(val -> body.put("video_quality_mode", val));
        defaultAutoArchiveDuration.ifPresent(val -> body.put("default_auto_archive_duration", val));
        defaultReactionEmoji.ifPresent(val -> body.put("default_reaction_emoji", val));
        availableTags.ifPresent(val -> body.put("available_tags", val));
        defaultSortOrder.ifPresent(val -> body.put("default_sort_order", val));
        defaultForumLayout.ifPresent(val -> body.put("default_forum_layout", val));
        defaultThreadRateLimitPerUser.ifPresent(
            val -> body.put("default_thread_rate_limit_per_user", val)
        );

        return new DiscordRequestBuilder()
            .post()
            .path("/guilds/%s/channels".formatted(guildId))
            .body(body);
    }
}
