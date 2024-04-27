package com.javadiscord.jdi.api.guild;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

/** Deprecated in favor of ModifyCurrentMemberRequest */
@Deprecated
public record ModifyCurrentUserNickRequest(long guildId, Optional<String> nick)
    implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        nick.ifPresent(val -> body.put("nick", val));

        return new DiscordRequestBuilder()
            .patch()
            .path("/guilds/%s/members/@me/nick".formatted(guildId))
            .body(body);
    }
}
