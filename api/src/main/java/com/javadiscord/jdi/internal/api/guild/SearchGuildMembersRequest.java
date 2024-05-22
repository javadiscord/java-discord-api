package com.javadiscord.jdi.internal.api.guild;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record SearchGuildMembersRequest(
    long guildId,
    Optional<String> query,
    Optional<Integer> limit
) implements DiscordRequest {

    public SearchGuildMembersRequest {
        if (limit.isPresent()) {
            int value = limit.get();
            if (value < 1 || value > 1000) {
                throw new IllegalArgumentException("Limit must be between 1 and 1,000");
            }
        }
    }

    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder discordRequestBuilder
            = new DiscordRequestBuilder()
                .get()
                .path("/guilds/%s/members/search".formatted(guildId));

        query.ifPresent(val -> discordRequestBuilder.queryParam("query", val));
        limit.ifPresent(val -> discordRequestBuilder.queryParam("limit", val));

        return discordRequestBuilder;
    }
}
