package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.guild_template.CreateGuildFromTemplateRequest;

public class CreateGuildFromTemplateBuilder {
    private final String templateCode;
    private final String name;
    private Optional<String> icon;

    public CreateGuildFromTemplateBuilder(String templateCode, String name) {
        this.templateCode = templateCode;
        this.name = name;
        this.icon = Optional.empty();
    }

    public CreateGuildFromTemplateBuilder icon(String icon) {
        this.icon = Optional.of(icon);
        return this;
    }

    public CreateGuildFromTemplateRequest build() {
        return new CreateGuildFromTemplateRequest(templateCode, name, icon);
    }

}
