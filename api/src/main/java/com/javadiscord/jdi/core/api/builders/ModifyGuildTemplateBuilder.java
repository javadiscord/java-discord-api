package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.guild_template.ModifyGuildTemplateRequest;

public class ModifyGuildTemplateBuilder {
    private final String templateCode;
    private Optional<String> name;
    private Optional<String> description;
    private long guildId;

    public ModifyGuildTemplateBuilder(String templateCode) {
        this.templateCode = templateCode;
        this.name = Optional.empty();
        this.description = Optional.empty();
    }

    public ModifyGuildTemplateBuilder name(String name) {
        this.name = Optional.of(name);
        return this;
    }

    public ModifyGuildTemplateBuilder description(String description) {
        this.description = Optional.of(description);
        return this;
    }

    public ModifyGuildTemplateBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public ModifyGuildTemplateRequest build() {
        return new ModifyGuildTemplateRequest(guildId, templateCode, name, description);
    }
}
