package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.guild.ModifyCurrentMemberRequest;

import java.util.Optional;

public final class ModifyCurrentMemberRequestBuilder {
    private long guildId;
    private Optional<String> nick;

    public ModifyCurrentMemberRequestBuilder() {
        this.nick = Optional.empty();
    }

    public ModifyCurrentMemberRequestBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public ModifyCurrentMemberRequestBuilder nick(String nick) {
        this.nick = Optional.of(nick);
        return this;
    }

    public ModifyCurrentMemberRequest build() {
        return new ModifyCurrentMemberRequest(guildId, nick);
    }
}
