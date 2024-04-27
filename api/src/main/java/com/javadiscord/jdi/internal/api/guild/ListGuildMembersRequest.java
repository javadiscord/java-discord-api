package com.javadiscord.jdi.internal.api.guild;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.Optional;

public record ListGuildMembersRequest(
        long guildId,
        Optional<Integer> limit,
        Optional<Long>
                after // https://discord.com/developers/docs/resources/guild#list-guild-members
        // read that to see what after does
        ) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder discordRequestBuilder =
                new DiscordRequestBuilder().get().path("/guilds/%s/members".formatted(guildId));

        limit.ifPresent(val -> discordRequestBuilder.queryParam("limit", val));
        after.ifPresent(val -> discordRequestBuilder.queryParam("after", val));

        return discordRequestBuilder;
    }
}
