package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.guild.CreateGuildRoleRequest;

import java.util.Optional;

public class CreateGuildRoleBuilder {
    private long guildId;
    private Optional<String> name;
    private Optional<String> permissions;
    private Optional<Integer> color;
    private Optional<Boolean> hoist;
    private Optional<String> icon;
    private Optional<String> unicodeEmoji;
    private Optional<Boolean> mentionable;

    public CreateGuildRoleBuilder() {
        this.name = Optional.empty();
        this.permissions = Optional.empty();
        this.color = Optional.empty();
        this.hoist = Optional.empty();
        this.icon = Optional.empty();
        this.unicodeEmoji = Optional.empty();
        this.mentionable = Optional.empty();
    }

    public CreateGuildRoleBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public CreateGuildRoleBuilder name(String name) {
        this.name = Optional.of(name);
        return this;
    }

    public CreateGuildRoleBuilder permissions(String permissions) {
        this.permissions = Optional.of(permissions);
        return this;
    }

    public CreateGuildRoleBuilder color(int color) {
        this.color = Optional.of(color);
        return this;
    }

    public CreateGuildRoleBuilder hoist(boolean hoist) {
        this.hoist = Optional.of(hoist);
        return this;
    }

    public CreateGuildRoleBuilder icon(String icon) {
        this.icon = Optional.of(icon);
        return this;
    }

    public CreateGuildRoleBuilder unicodeEmoji(String unicodeEmoji) {
        this.unicodeEmoji = Optional.of(unicodeEmoji);
        return this;
    }

    public CreateGuildRoleBuilder mentionable(boolean mentionable) {
        this.mentionable = Optional.of(mentionable);
        return this;
    }

    public CreateGuildRoleRequest build() {
        return new CreateGuildRoleRequest(
                guildId, name, permissions, color, hoist, icon, unicodeEmoji, mentionable);
    }
}
