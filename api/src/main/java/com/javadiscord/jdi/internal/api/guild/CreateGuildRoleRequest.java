package com.javadiscord.jdi.internal.api.guild;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record CreateGuildRoleRequest(
    long guildId,
    Optional<String> name,
    Optional<String> permissions,
    Optional<Integer> color,
    Optional<Boolean> hoist,
    Optional<String> icon,
    Optional<String> unicodeEmoji,
    Optional<Boolean> mentionable
) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        name.ifPresent(val -> body.put("name", val));
        permissions.ifPresent(val -> body.put("permissions", val));
        color.ifPresent(val -> body.put("color", val));
        hoist.ifPresent(val -> body.put("hoist", val));
        icon.ifPresent(val -> body.put("icon", val));
        unicodeEmoji.ifPresent(val -> body.put("unicode_emoji", val));
        mentionable.ifPresent(val -> body.put("mentionable", val));

        return new DiscordRequestBuilder().post().path("/guilds/%s/roles").body(body);
    }
}
