package com.javadiscord.jdi.internal.request;

import com.javadiscord.jdi.core.models.emoji.Emoji;
import com.javadiscord.jdi.internal.api.emojis.*;
import com.javadiscord.jdi.internal.request.builders.ModifyEmojiBuilder;
import com.javadiscord.jdi.internal.response.AsyncResponse;
import com.javadiscord.jdi.internal.response.DiscordResponseParser;

import java.util.List;

public class EmojiRequest {
    private final DiscordResponseParser responseParser;

    public EmojiRequest(DiscordResponseParser responseParser) {
        this.responseParser = responseParser;
    }

    public AsyncResponse<Emoji> createEmoji(
            long guildId, String name, String image, List<Long> roles) {
        return responseParser.callAndParse(
                Emoji.class, new CreateEmojiRequest(guildId, name, image, roles));
    }

    public AsyncResponse<Emoji> deleteEmoji(long guildId, long emojiId) {
        return responseParser.callAndParse(Emoji.class, new DeleteEmojiRequest(guildId, emojiId));
    }

    public AsyncResponse<Emoji> getEmoji(long guildId, long emojiId) {
        return responseParser.callAndParse(Emoji.class, new GetEmojiRequest(guildId, emojiId));
    }

    public AsyncResponse<Emoji> getEmojis(long guildId) {
        return responseParser.callAndParse(Emoji.class, new GetEmojisRequest(guildId));
    }

    public AsyncResponse<Emoji> modifyEmoji(ModifyEmojiBuilder builder) {
        return responseParser.callAndParse(Emoji.class, builder.build());
    }
}
