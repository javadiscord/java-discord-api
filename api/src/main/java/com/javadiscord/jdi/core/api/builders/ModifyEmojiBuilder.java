package com.javadiscord.jdi.core.api.builders;

import java.util.List;
import java.util.Optional;

import com.javadiscord.jdi.internal.api.emojis.ModifyEmojiRequest;

public class ModifyEmojiBuilder {
    private long guildId;
    private final long emojiId;
    private Optional<String> name;
    private Optional<List<Long>> roles;

    public ModifyEmojiBuilder(long emojiId) {
        this.emojiId = emojiId;
        this.name = Optional.empty();
        this.roles = Optional.empty();
    }

    public ModifyEmojiBuilder name(String name) {
        this.name = Optional.of(name);
        return this;
    }

    public ModifyEmojiBuilder roles(List<Long> roles) {
        this.roles = Optional.of(roles);
        return this;
    }

    public ModifyEmojiRequest build() {
        return new ModifyEmojiRequest(guildId, emojiId, name, roles);
    }

    public ModifyEmojiBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }
}
