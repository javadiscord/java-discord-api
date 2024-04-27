package com.javadiscord.jdi.api.guild;

import com.javadiscord.jdi.api.DiscordRequest;
import com.javadiscord.jdi.api.DiscordRequestBuilder;

public record GetGuildOnboardingRequest(long guildId) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder().get().path("/guilds/%s/onboarding".formatted(guildId));
    }
}
