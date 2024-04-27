package com.javadiscord.jdi.internal.api.guild;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record GetGuildPruneCountRequest(
    long guildId, Optional<Integer> days, Optional<List<Long>> includeRoles
)
    implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder discordRequestBuilder = new DiscordRequestBuilder().get()
            .path("/guilds/%s/prune".formatted(guildId));

        days.ifPresent(val -> discordRequestBuilder.queryParam("days", val));
        includeRoles.ifPresent(
            val -> discordRequestBuilder.queryParam(
                "include_roles",
                val.stream()
                    .map(Object::toString)
                    .collect(Collectors.joining(","))
            )
        );

        return discordRequestBuilder;
    }
}
