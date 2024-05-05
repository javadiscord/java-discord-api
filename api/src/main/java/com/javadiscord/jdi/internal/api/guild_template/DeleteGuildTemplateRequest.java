package com.javadiscord.jdi.internal.api.guild_template;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record DeleteGuildTemplateRequest(long guildId, String templateCode)
        implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .delete()
                .path("/guilds/%s/templates/%s".formatted(guildId, templateCode));
    }
}
