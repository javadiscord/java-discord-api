package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.guild.CreateGuildBanRequest;

import java.util.Optional;

public class GuildBanBuilder {
    private long guildId;
    private final long userId;
    private Optional<Integer> deleteMessageSeconds;

    public GuildBanBuilder(long userId) {
        this.userId = userId;
        this.deleteMessageSeconds = Optional.empty();
    }

    public GuildBanBuilder setGuildId(long guildId) {
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
