package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.guild.ModifyCurrentUserNickRequest;

import java.util.Optional;

public final class ModifyCurrentUserNickRequestBuilder {
    private long guildId;
    private Optional<String> nick;

    public ModifyCurrentUserNickRequestBuilder() {
        this.nick = Optional.empty();
    }

    public ModifyCurrentUserNickRequestBuilder setGuildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public ModifyCurrentUserNickRequestBuilder setNick(String nick) {
        this.nick = Optional.of(nick);
        return this;
    }

    public ModifyCurrentUserNickRequest build() {
        return new ModifyCurrentUserNickRequest(guildId, nick);
    }
}
