package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.guild.BulkGuildBanRequest;

import java.util.List;
import java.util.Optional;

public class BulkGuildBanBuilder {
    private long guildId;
    private final List<Long> userIds;
    private Optional<Integer> deleteMessageSeconds;

    public BulkGuildBanBuilder(List<Long> userIds) {
        this.userIds = userIds;
        this.deleteMessageSeconds = Optional.empty();
    }

    public BulkGuildBanBuilder deleteMessageSeconds(Optional<Integer> deleteMessageSeconds) {
        this.deleteMessageSeconds = deleteMessageSeconds;
        return this;
    }

    public BulkGuildBanBuilder setGuildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public BulkGuildBanRequest build() {
        return new BulkGuildBanRequest(guildId, userIds, deleteMessageSeconds);
    }
}
