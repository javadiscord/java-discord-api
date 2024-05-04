package com.javadiscord.jdi.core.api;

import com.javadiscord.jdi.core.api.builders.ModifyEmojiBuilder;
import com.javadiscord.jdi.core.models.emoji.Emoji;
import com.javadiscord.jdi.internal.api.emojis.*;

import java.util.List;

public class EmojiRequest {
    private final DiscordResponseParser responseParser;
    private final long guildId;

    public EmojiRequest(DiscordResponseParser responseParser, long guildId) {
        this.responseParser = responseParser;
        this.guildId = guildId;
    }

    public AsyncResponse<Emoji> createEmoji(String name, String image, List<Long> roles) {
        return responseParser.callAndParse(
                Emoji.class, new CreateEmojiRequest(guildId, name, image, roles));
    }

    public AsyncResponse<Emoji> deleteEmoji(long emojiId) {
        return responseParser.callAndParse(Emoji.class, new DeleteEmojiRequest(guildId, emojiId));
    }

    public AsyncResponse<Emoji> getEmoji(long emojiId) {
        return responseParser.callAndParse(Emoji.class, new GetEmojiRequest(guildId, emojiId));
    }

    public AsyncResponse<Emoji> getEmojis() {
        return responseParser.callAndParse(Emoji.class, new GetEmojisRequest(guildId));
    }

    public AsyncResponse<Emoji> modifyEmoji(ModifyEmojiBuilder builder) {
        return responseParser.callAndParse(Emoji.class, builder.guildId(guildId).build());
    }
}
