package com.javadiscord.jdi.internal.api.guild;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public record ModifyGuildMemberRequest(
        long guildId,
        long userId,
        Optional<String> nick,
        Optional<List<Long>> roles,
        Optional<Boolean> mute,
        Optional<Boolean> deafen,
        Optional<Long> channelId,
        Optional<OffsetDateTime> communicationDisabledUntil,
        Optional<Integer> flags)
        implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        nick.ifPresent(val -> body.put("nick", val));
        roles.ifPresent(val -> body.put("roles", val));
        mute.ifPresent(val -> body.put("mute", val));
        deafen.ifPresent(val -> body.put("deaf", val));
        channelId.ifPresent(val -> body.put("channel_id", val));
        communicationDisabledUntil.ifPresent(val -> body.put("communication_disabled_until", val));
        flags.ifPresent(val -> body.put("flags", val));

        return new DiscordRequestBuilder()
                .patch()
                .path("/guilds/%s/members/%s".formatted(guildId, userId))
                .body(body);
    }
}
