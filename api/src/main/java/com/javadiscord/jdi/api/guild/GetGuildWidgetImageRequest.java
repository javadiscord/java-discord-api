package com.javadiscord.jdi.api.guild;

import java.util.Optional;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

public record GetGuildWidgetImageRequest(
    long guildId,
    Optional<String> style // TODO:
                           // https://discord.com/developers/docs/resources/guild#get-guild-widget-image-widget-style-options
) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        DiscordRequestBuilder discordRequestBuilder = new DiscordRequestBuilder().get()
            .path("/guilds/%s/widget.png".formatted(guildId));

        style.ifPresent(val -> discordRequestBuilder.queryParam("style", val));

        return discordRequestBuilder;
    }
}
