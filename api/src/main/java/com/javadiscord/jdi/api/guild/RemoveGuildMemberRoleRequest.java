package com.javadiscord.jdi.api.guild;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

public record RemoveGuildMemberRoleRequest(long guildId, long userId, long roleId)
    implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .delete()
            .path("/guilds/%s/members/%s/roles/%s".formatted(guildId, userId, roleId));
    }
}
