package com.javadiscord.jdi.internal.api.guild;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record ModifyCurrentMemberRequest(long guildId, Optional<String> nick)
    implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        nick.ifPresent(val -> body.put("nick", val));

        return new DiscordRequestBuilder()
            .patch()
            .path("/guilds/%s/members/@me".formatted(guildId))
            .body(body);
    }
}
