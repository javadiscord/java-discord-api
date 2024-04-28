package com.javadiscord.jdi.core.request.builders;

import com.javadiscord.jdi.internal.api.sticker.ModifyGuildStickerRequest;

import java.util.Optional;

public class ModifyGuildStickerBuilder {
    private final long guildId;
    private final long stickerId;
    private Optional<String> name;
    private Optional<String> description;
    private Optional<String> tags;

    public ModifyGuildStickerBuilder(long guildId, long stickerId) {
        this.guildId = guildId;
        this.stickerId = stickerId;
        name = Optional.empty();
        description = Optional.empty();
        tags = Optional.empty();
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
}
