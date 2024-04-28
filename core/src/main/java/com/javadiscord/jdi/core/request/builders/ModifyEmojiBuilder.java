package com.javadiscord.jdi.core.request.builders;

import com.javadiscord.jdi.internal.api.emojis.ModifyEmojiRequest;

import java.util.List;
import java.util.Optional;

public class ModifyEmojiBuilder {
    private final long guildId;
    private final long emojiId;
    private Optional<String> name;
    private Optional<List<Long>> roles;

    public ModifyEmojiBuilder(long guildId, long emojiId) {
        this.guildId = guildId;
        this.emojiId = emojiId;
        name = Optional.empty();
        roles = Optional.empty();
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
}
