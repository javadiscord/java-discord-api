package com.javadiscord.jdi.internal.api.guild;

import com.javadiscord.jdi.core.models.guild.WidgetStyleOptions;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.Optional;

public record GetGuildWidgetImageRequest(long guildId, Optional<WidgetStyleOptions> style)
        implements DiscordRequest {
    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder discordRequestBuilder =
                new DiscordRequestBuilder().get().path("/guilds/%s/widget.png".formatted(guildId));

        style.ifPresent(val -> discordRequestBuilder.queryParam("style", val.name()));

        return discordRequestBuilder;
    }
}
