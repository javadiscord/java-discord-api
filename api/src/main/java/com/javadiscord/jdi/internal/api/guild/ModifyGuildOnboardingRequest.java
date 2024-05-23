package com.javadiscord.jdi.internal.api.guild;

import java.util.List;
import java.util.Map;

import com.javadiscord.jdi.core.models.guild.OnboardingMode;
import com.javadiscord.jdi.core.models.guild.OnboardingPrompt;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record ModifyGuildOnboardingRequest(
    long guildId,
    List<OnboardingPrompt> prompts,
    List<Long> defaultChannelIds,
    boolean enabled,
    OnboardingMode mode
) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
            .put()
            .path("/guilds/%s/onboarding")
            .body(
                Map.of(
                    "prompts", prompts,
                    "default_channel_ids", defaultChannelIds,
                    "enabled", enabled,
                    "mode", mode
                )
            );
    }
}
