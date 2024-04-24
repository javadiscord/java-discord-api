package com.javadiscord.jdi.internal.api.impl.guild;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record GetGuildBansRequest(
    long guildId, Optional<Integer> limit, Optional<Long> before, Optional<Long> after
)
    implements DiscordRequest {

    public GetGuildBansRequest {
        if (limit.isPresent() && limit.get() > 1000) {
            throw new IllegalArgumentException("Limit cannot be more than 1,000");
        }
    }

    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder discordRequestBuilder = new DiscordRequestBuilder().get()
            .path("/guilds/%s/bans".formatted(guildId));

        limit.ifPresent(val -> discordRequestBuilder.queryParam("limit", val));
        before.ifPresent(val -> discordRequestBuilder.queryParam("before", val));
        after.ifPresent(val -> discordRequestBuilder.queryParam("after", val));

        return discordRequestBuilder;
    }
}
