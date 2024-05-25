package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.guild.ModifyCurrentUserNickRequest;

public final class ModifyCurrentUserNickBuilder {
    private long guildId;
    private Optional<String> nick;

    public ModifyCurrentUserNickBuilder() {
        this.nick = Optional.empty();
    }

    public ModifyCurrentUserNickBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public ModifyCurrentUserNickBuilder nick(String nick) {
        this.nick = Optional.of(nick);
        return this;
    }

    public ModifyCurrentUserNickRequest build() {
        return new ModifyCurrentUserNickRequest(guildId, nick);
    }
}
