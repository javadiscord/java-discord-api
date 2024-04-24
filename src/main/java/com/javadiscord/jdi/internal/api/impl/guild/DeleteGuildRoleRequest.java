package com.javadiscord.jdi.internal.api.impl.guild;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record DeleteGuildRoleRequest(long guildId, long roleId) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .delete()
            .path("/guilds/%s/roles/%s".formatted(guildId, roleId));
    }
}