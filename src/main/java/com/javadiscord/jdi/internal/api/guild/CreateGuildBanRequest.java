package com.javadiscord.jdi.internal.api.guild;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record CreateGuildBanRequest(
    long guildId, long userId, Optional<Integer> deleteMessageSeconds
)
    implements DiscordRequest {

    public CreateGuildBanRequest {
        if (deleteMessageSeconds.isPresent() && deleteMessageSeconds.get() > 604800) {
            throw new IllegalArgumentException("Delete message seconds cannot be more than 7 days");
        }
    }

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        deleteMessageSeconds.ifPresent(val -> body.put("delete_message_seconds", val));

        return new DiscordRequestBuilder()
            .put()
            .path("/guilds/%s/bans/%s".formatted(guildId, userId))
            .body(body);
    }
}
