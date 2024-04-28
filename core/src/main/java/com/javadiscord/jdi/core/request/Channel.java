package com.javadiscord.jdi.core.request;

import com.javadiscord.jdi.core.request.builders.CreateMessageBuilder;
import com.javadiscord.jdi.internal.api.channel.*;

import java.util.List;

public class Channel {

    public AddThreadMemberRequest addThreadMemberRequest(long channelId, long userId) {
        return new AddThreadMemberRequest(channelId, userId);
    }

    public BulkDeleteMessagesRequest bulkDeleteMessages(long channelId, List<Long> messageIds) {
        return new BulkDeleteMessagesRequest(channelId, messageIds);
    }

    public ChannelCreateInviteRequest createInvite(
            long channelId, int maxAge, int maxUses, boolean temporary) {
        return new ChannelCreateInviteRequest(channelId, maxAge, maxUses, temporary);
    }

    public CreateMessageRequest createMessage(CreateMessageBuilder builder) {
        return builder.build();
    }

    public CreateReactionRequest createReaction(long channelId, long messageId, String emoji) {
        return new CreateReactionRequest(channelId, messageId, emoji);
    }

    public CrossPostMessageRequest crossPostMessage(long channelId, long messageId) {
        return new CrossPostMessageRequest(channelId, messageId);
    }

    public DeleteAllReactionsForEmojiRequest deleteAllReactionsForEmoji(
            long channelId, long messageId, String emoji) {
        return new DeleteAllReactionsForEmojiRequest(channelId, messageId, emoji);
    }
}
