package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.guild.CreateGuildBanRequest;

public class GuildBanBuilder {
    private long guildId;
    private final long userId;
    private Optional<Integer> deleteMessageSeconds;

    public GuildBanBuilder(long userId) {
        this.userId = userId;
        this.deleteMessageSeconds = Optional.empty();
    }

    public GuildBanBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public GuildBanBuilder deleteMessageSeconds(int deleteMessageSeconds) {
        this.deleteMessageSeconds = Optional.of(deleteMessageSeconds);
        return this;
    }

    public CreateGuildBanRequest build() {
        return new CreateGuildBanRequest(guildId, userId, deleteMessageSeconds);
    }
}
