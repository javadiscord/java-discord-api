package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.guild.ModifyCurrentMemberRequest;

import java.util.Optional;

public final class ModifyCurrentMemberBuilder {
    private long guildId;
    private Optional<String> nick;

    public ModifyCurrentMemberBuilder() {
        this.nick = Optional.empty();
    }

    public ModifyCurrentMemberBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public ModifyCurrentMemberBuilder nick(String nick) {
        this.nick = Optional.of(nick);
        return this;
    }

    public ModifyCurrentMemberRequest build() {
        return new ModifyCurrentMemberRequest(guildId, nick);
    }
}
