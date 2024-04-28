package com.javadiscord.jdi.internal.request;

import com.javadiscord.jdi.internal.api.guild.AddGuildMemberRoleRequest;
import com.javadiscord.jdi.internal.api.guild.BeginGuildPruneRequest;
import com.javadiscord.jdi.internal.response.DiscordResponseParser;

import java.util.List;

public class GuildRequest {
    private final DiscordResponseParser responseParser;

    public GuildRequest(DiscordResponseParser responseParser) {
        this.responseParser = responseParser;
    }

    public AddGuildMemberRoleRequest addGuildMemberRole(long guildId, long userId, long roleId) {
        return new AddGuildMemberRoleRequest(guildId, userId, roleId);
    }

    public BeginGuildPruneRequest beginGuildPrune(
            long guildId, int days, boolean computePruneCount, List<Long> includeRoles) {
        return new BeginGuildPruneRequest(guildId, days, computePruneCount, includeRoles);
    }
}
