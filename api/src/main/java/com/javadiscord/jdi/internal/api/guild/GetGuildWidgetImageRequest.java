package com.javadiscord.jdi.internal.api.guild;

import com.javadiscord.jdi.core.models.guild.WidgetStyle;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.Optional;

public record GetGuildWidgetImageRequest(long guildId, Optional<WidgetStyle> style)
        implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder discordRequestBuilder =
                new DiscordRequestBuilder().get().path("/guilds/%s/widget.png".formatted(guildId));

        style.ifPresent(val -> discordRequestBuilder.queryParam("style", val.name().toLowerCase()));

        return discordRequestBuilder;
    }
}
