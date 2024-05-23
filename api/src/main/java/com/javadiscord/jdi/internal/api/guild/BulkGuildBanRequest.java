package com.javadiscord.jdi.internal.api.guild;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record BulkGuildBanRequest(
    long guildId,
    List<Long> userIds,
    Optional<Integer> deleteMessageSeconds
) implements DiscordRequest {

    public BulkGuildBanRequest {
        if (userIds.size() > 200) {
            throw new IllegalArgumentException("You cannot ban more than 200 guild members");
        }

        if (deleteMessageSeconds.isPresent() && deleteMessageSeconds.get() > 604800) {
            throw new IllegalArgumentException("Delete message seconds cannot be more than 7 days");
        }
    }

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        body.put("user_ids", userIds);
        deleteMessageSeconds.ifPresent(val -> body.put("delete_message_seconds", val));

        return new DiscordRequestBuilder()
            .post()
            .path("/guilds/%s/bulk-ban".formatted(guildId))
            .body(body);
    }
}
