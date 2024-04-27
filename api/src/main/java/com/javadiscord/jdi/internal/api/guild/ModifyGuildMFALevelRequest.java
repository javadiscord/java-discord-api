package com.javadiscord.jdi.internal.api.guild;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;
import com.javadiscord.jdi.internal.models.guild.MFALevel;

import java.util.Map;

public record ModifyGuildMFALevelRequest(long guildId, MFALevel level) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .post()
                .path("/guilds/%s/mfa".formatted(guildId))
                .body(Map.of("level", level));
    }
}
