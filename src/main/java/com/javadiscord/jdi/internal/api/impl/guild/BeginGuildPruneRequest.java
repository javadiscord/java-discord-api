package com.javadiscord.jdi.internal.api.impl.guild;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.List;
import java.util.Map;

// https://discord.com/developers/docs/resources/guild#begin-guild-prune
// it has warnings and stuff so read that
public record BeginGuildPruneRequest(
        long guildId, int days, boolean computePruneCount, List<Long> includeRoles)
        implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .post()
                .path("/guilds/%s/prune".formatted(guildId))
                .body(
                        Map.of(
                                "days", days,
                                "compute_prune_count", computePruneCount,
                                "include_roles", includeRoles));
    }
}
