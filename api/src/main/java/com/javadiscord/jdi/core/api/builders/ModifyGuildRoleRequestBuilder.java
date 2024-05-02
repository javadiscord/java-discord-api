package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.guild.ModifyGuildRoleRequest;

import java.util.Optional;

public final class ModifyGuildRoleRequestBuilder {
    private long guildId;
    private final long roleId;
    private Optional<String> name;
    private Optional<String> permissions;
    private Optional<Integer> color;
    private Optional<Boolean> hoist;
    private Optional<String> icon;
    private Optional<String> unicodeEmoji;
    private Optional<Boolean> mentionable;

    public ModifyGuildRoleRequestBuilder(long roleId) {
        this.roleId = roleId;
        this.name = Optional.empty();
        this.permissions = Optional.empty();
        this.color = Optional.empty();
        this.hoist = Optional.empty();
        this.icon = Optional.empty();
        this.unicodeEmoji = Optional.empty();
        this.mentionable = Optional.empty();
    }

    public ModifyGuildRoleRequestBuilder setGuildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public ModifyGuildRoleRequestBuilder setName(String name) {
        this.name = Optional.of(name);
        return this;
    }

    public ModifyGuildRoleRequestBuilder setPermissions(String permissions) {
        this.permissions = Optional.of(permissions);
        return this;
    }

    public ModifyGuildRoleRequestBuilder setColor(int color) {
        this.color = Optional.of(color);
        return this;
    }

    public ModifyGuildRoleRequestBuilder setHoist(boolean hoist) {
        this.hoist = Optional.of(hoist);
        return this;
    }

    public ModifyGuildRoleRequestBuilder setIcon(String icon) {
        this.icon = Optional.of(icon);
        return this;
    }

    public ModifyGuildRoleRequestBuilder setUnicodeEmoji(String unicodeEmoji) {
        this.unicodeEmoji = Optional.of(unicodeEmoji);
        return this;
    }

    public ModifyGuildRoleRequestBuilder setMentionable(boolean mentionable) {
        this.mentionable = Optional.of(mentionable);
        return this;
    }

    public ModifyGuildRoleRequest build() {
        return new ModifyGuildRoleRequest(
                guildId, roleId, name, permissions, color, hoist, icon, unicodeEmoji, mentionable);
    }
}
