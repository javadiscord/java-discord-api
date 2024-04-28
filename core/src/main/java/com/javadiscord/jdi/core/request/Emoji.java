package com.javadiscord.jdi.core.request;

import com.javadiscord.jdi.core.request.builders.ModifyEmojiBuilder;
import com.javadiscord.jdi.internal.api.emojis.*;

import java.util.List;

public class Emoji {

    public CreateEmojiRequest createEmoji(
            long guildId, String name, String image, List<Long> roles) {
        return new CreateEmojiRequest(guildId, name, image, roles);
    }

    public DeleteEmojiRequest deleteEmoji(long guildId, long emojiId) {
        return new DeleteEmojiRequest(guildId, emojiId);
    }

    public GetEmojiRequest getEmoji(long guildId, long emojiId) {
        return new GetEmojiRequest(guildId, emojiId);
    }

    public GetEmojisRequest getEmojis(long guildId) {
        return new GetEmojisRequest(guildId);
    }

    public ModifyEmojiRequest modifyEmoji(ModifyEmojiBuilder builder) {
        return builder.build();
    }
}
