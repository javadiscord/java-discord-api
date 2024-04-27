package com.javadiscord.jdi.api.sticker;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

public record ModifyGuildStickerRequest(
    long guildId,
    long stickerId,
    Optional<String> name,
    Optional<String> description,
    Optional<String> tags
)
    implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        name.ifPresent(val -> body.put("name", val));
        description.ifPresent(val -> body.put("description", val));
        tags.ifPresent(val -> body.put("tags", val));

        return new DiscordRequestBuilder()
            .patch()
            .path("/guilds/%s/stickers/%s".formatted(guildId, stickerId))
            .body(body);
    }
}
