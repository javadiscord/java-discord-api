package com.javadiscord.jdi.core.api.builders;

import java.util.List;
import java.util.Optional;

import com.javadiscord.jdi.internal.api.guild.BulkGuildBanRequest;

public class BulkGuildBanBuilder {
    private long guildId;
    private final List<Long> userIds;
    private Optional<Integer> deleteMessageSeconds;

    public BulkGuildBanBuilder(List<Long> userIds) {
        this.userIds = userIds;
        this.deleteMessageSeconds = Optional.empty();
    }

    public BulkGuildBanBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public BulkGuildBanBuilder deleteMessageSeconds(int deleteMessageSeconds) {
        this.deleteMessageSeconds = Optional.of(deleteMessageSeconds);
        return this;
    }

    public BulkGuildBanRequest build() {
        return new BulkGuildBanRequest(guildId, userIds, deleteMessageSeconds);
    }
}
