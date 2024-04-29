package com.javadiscord.jdi.internal.api.guild;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record AddGuildMemberRoleRequest(long guildId, long userId, long roleId)
        implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .put()
                .path("/guilds/%s/members/%s/roles/%s".formatted(guildId, userId, roleId));
    }
}
