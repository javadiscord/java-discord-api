package com.javadiscord.jdi.internal.api.impl.guild;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;
import com.javadiscord.jdi.internal.models.guild.Role;

import java.util.*;

// Only works for bots in less than 10 guilds (https://discord.com/developers/docs/resources/guild#create-guild)
public record CreateGuildRequest(
        String name,
        Optional<String> icon,
        Optional<Integer> verificationLevel,
        Optional<Integer> defaultMessageNotifications,
        Optional<Integer> explicitContentFilter,
        Optional<List<Role>> roles,
        //Optional<List<PartialChannel>> channels, // TODO: future developer implement this
        Optional<Long> afkChannelId,
        Optional<Integer> afkTimeout,
        Optional<Long> systemChannelId,
        Optional<Integer> systemChannelFlags
) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        body.put("name", name);
        icon.ifPresent(val -> body.put("icon", val));
        verificationLevel.ifPresent(val -> body.put("verification_level", val));
        defaultMessageNotifications.ifPresent(val -> body.put("default_message_notifications", val));
        explicitContentFilter.ifPresent(val -> body.put("explicit_content_filter", val));
        roles.ifPresent(val -> body.put("roles", val));
        //channels.ifPresent(val -> body.put("channels", val));
        afkChannelId.ifPresent(val -> body.put("afk_channel_id", val));
        afkTimeout.ifPresent(val -> body.put("afk_timeout", val));
        systemChannelId.ifPresent(val -> body.put("system_channel_id", systemChannelId));
        systemChannelFlags.ifPresent(val -> body.put("system_channel_flags", systemChannelFlags));

        return new DiscordRequestBuilder()
                .post()
                .path("/guilds")
                .body(body);
    }
}
