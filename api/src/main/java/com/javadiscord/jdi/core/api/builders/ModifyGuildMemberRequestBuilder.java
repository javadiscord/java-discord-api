package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.guild.ModifyGuildMemberRequest;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public final class ModifyGuildMemberRequestBuilder {
    private long guildId;
    private final long userId;
    private Optional<String> nick;
    private Optional<List<Long>> roles;
    private Optional<Boolean> mute;
    private Optional<Boolean> deafen;
    private Optional<Long> channelId;
    private Optional<OffsetDateTime> communicationDisabledUntil;
    private Optional<Integer> flags;

    public ModifyGuildMemberRequestBuilder(long userId) {
        this.userId = userId;
        this.nick = Optional.empty();
        this.roles = Optional.empty();
        this.mute = Optional.empty();
        this.deafen = Optional.empty();
        this.channelId = Optional.empty();
        this.communicationDisabledUntil = Optional.empty();
        this.flags = Optional.empty();
    }

    public ModifyGuildMemberRequestBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public ModifyGuildMemberRequestBuilder nick(String nick) {
        this.nick = Optional.of(nick);
        return this;
    }

    public ModifyGuildMemberRequestBuilder roles(List<Long> roles) {
        this.roles = Optional.of(roles);
        return this;
    }

    public ModifyGuildMemberRequestBuilder mute(boolean mute) {
        this.mute = Optional.of(mute);
        return this;
    }

    public ModifyGuildMemberRequestBuilder deafen(boolean deafen) {
        this.deafen = Optional.of(deafen);
        return this;
    }

    public ModifyGuildMemberRequestBuilder channelId(long channelId) {
        this.channelId = Optional.of(channelId);
        return this;
    }

    public ModifyGuildMemberRequestBuilder communicationDisabledUntil(
            OffsetDateTime communicationDisabledUntil) {
        this.communicationDisabledUntil = Optional.of(communicationDisabledUntil);
        return this;
    }

    public ModifyGuildMemberRequestBuilder flags(int flags) {
        this.flags = Optional.of(flags);
        return this;
    }

    public ModifyGuildMemberRequest build() {
        return new ModifyGuildMemberRequest(
                guildId,
                userId,
                nick,
                roles,
                mute,
                deafen,
                channelId,
                communicationDisabledUntil,
                flags);
    }
}
