package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.guild_template.CreateGuildTemplateRequest;

public class CreateGuildTemplateBuilder {
    private final String name;
    private Optional<String> description;

    private long guildId;

    public CreateGuildTemplateBuilder(String name) {
        this.name = name;
        this.description = Optional.empty();
    }

    public CreateGuildTemplateBuilder description(String description) {
        this.description = Optional.of(description);
        return this;
    }

    public CreateGuildTemplateBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public CreateGuildTemplateRequest build() {
        return new CreateGuildTemplateRequest(guildId, name, description);
    }
}
