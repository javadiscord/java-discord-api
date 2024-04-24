package com.javadiscord.jdi.internal.api.impl.guild;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record GetGuildWidgetSettingsRequest(long guildId) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder().get().path("/guilds/%s/widget".formatted(guildId));
    }
}
