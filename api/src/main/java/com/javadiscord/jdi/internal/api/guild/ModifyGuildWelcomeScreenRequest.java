package com.javadiscord.jdi.internal.api.guild;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;
import com.javadiscord.jdi.internal.models.guild.WelcomeScreenChannel;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public record ModifyGuildWelcomeScreenRequest(
        long guildId,
        Optional<Boolean> enabled,
        Optional<WelcomeScreenChannel> welcomeChannels,
        Optional<String> description)
        implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        enabled.ifPresent(val -> body.put("enabled", val));
        welcomeChannels.ifPresent(val -> body.put("welcome_channels", val));
        description.ifPresent(val -> body.put("description", val));

        return new DiscordRequestBuilder()
                .patch()
                .path("/guilds/%s/welcome-screen".formatted(guildId))
                .body(body);
    }
}
