package com.javadiscord.jdi.internal.api.guild;

import java.util.Map;

import com.javadiscord.jdi.core.models.guild.MFALevel;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record ModifyGuildMFALevelRequest(long guildId, MFALevel level) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .post()
            .path("/guilds/%s/mfa".formatted(guildId))
            .body(Map.of("level", level));
    }
}
