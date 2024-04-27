package com.javadiscord.jdi.api.guild;

import java.util.Map;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;
import com.javadiscord.jdi.internal.models.guild.MFALevel;

public record ModifyGuildMFALevelRequest(long guildId, MFALevel level) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .post()
            .path("/guilds/%s/mfa".formatted(guildId))
            .body(Map.of("level", level));
    }
}
