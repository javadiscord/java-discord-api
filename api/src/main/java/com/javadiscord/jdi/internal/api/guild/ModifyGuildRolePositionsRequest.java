package com.javadiscord.jdi.internal.api.guild;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.*;

public record ModifyGuildRolePositionsRequest(long guildId, List<ModifyGuildRolePositionData> roles)
        implements DiscordRequest {

    @Override
    public DiscordRequestBuilder create() {
        List<Map<String, Object>> body = new ArrayList<>(roles.size());

        for (ModifyGuildRolePositionData roleData : roles) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", roleData.id);
            roleData.position.ifPresent(val -> data.put("position", val));
            body.add(data);
        }

        return new DiscordRequestBuilder()
                .patch()
                .path("/guilds/%s/roles".formatted(guildId))
                .body(body);
    }

    public record ModifyGuildRolePositionData(long id, Optional<Integer> position) {}
}
