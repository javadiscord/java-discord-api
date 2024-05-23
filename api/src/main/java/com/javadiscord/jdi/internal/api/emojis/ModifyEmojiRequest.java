package com.javadiscord.jdi.internal.api.emojis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record ModifyEmojiRequest(
    long guildId,
    long emojiId,
    Optional<String> name,
    Optional<List<Long>> roles
) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        name.ifPresent(val -> body.put("name", val));
        roles.ifPresent(val -> body.put("roles", val));

        return new DiscordRequestBuilder()
            .patch()
            .path("/guilds/%s/emojis/%s".formatted(guildId, emojiId))
            .body(body);
    }
}
