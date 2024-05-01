package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.sticker.ModifyGuildStickerRequest;

import java.util.Optional;

public class ModifyGuildStickerBuilder {
    private long guildId;
    private final long stickerId;
    private Optional<String> name;
    private Optional<String> description;
    private Optional<String> tags;

    public ModifyGuildStickerBuilder(long stickerId) {
        this.stickerId = stickerId;
        this.name = Optional.empty();
        this.description = Optional.empty();
        this.tags = Optional.empty();
    }

    public ModifyGuildStickerBuilder name(String name) {
        this.name = Optional.of(name);
        return this;
    }

    public ModifyGuildStickerBuilder description(String description) {
        this.description = Optional.of(description);
        return this;
    }

    public ModifyGuildStickerBuilder tags(String tags) {
        this.tags = Optional.of(tags);
        return this;
    }

    public ModifyGuildStickerRequest build() {
        return new ModifyGuildStickerRequest(guildId, stickerId, name, description, tags);
    }

    public ModifyGuildStickerBuilder setGuildId(long guildId) {
        this.guildId = guildId;
        return this;
    }
}
