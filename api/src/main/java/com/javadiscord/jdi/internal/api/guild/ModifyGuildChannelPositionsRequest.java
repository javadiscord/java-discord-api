package com.javadiscord.jdi.internal.api.guild;

import java.util.*;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

public record ModifyGuildChannelPositionsRequest(
    long guildId,
    List<ModifyChannelPositionData> channels
) implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        List<Map<String, Object>> body = new ArrayList<>(channels.size());

        for (ModifyChannelPositionData channelData : channels) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", channelData.id);
            channelData.position.ifPresent(val -> data.put("position", val));
            channelData.lockPositions.ifPresent(val -> data.put("lock_permissions", val));
            channelData.parentId.ifPresent(val -> data.put("parent_id", val));
            body.add(data);
        }

        return new DiscordRequestBuilder()
            .patch()
            .path("/guilds/%s/channels".formatted(guildId))
            .body(body);
    }

    public record ModifyChannelPositionData(
        long id,
        Optional<Integer> position,
        Optional<Boolean> lockPositions,
        Optional<Long> parentId
    ) {}
}
