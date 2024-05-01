package com.javadiscord.jdi.core.api;

import com.javadiscord.jdi.core.api.builders.*;
import com.javadiscord.jdi.core.models.channel.Channel;
import com.javadiscord.jdi.core.models.channel.ChannelMention;
import com.javadiscord.jdi.core.models.channel.ThreadMember;
import com.javadiscord.jdi.core.models.invite.Invite;
import com.javadiscord.jdi.core.models.message.Message;
import com.javadiscord.jdi.core.models.message.MessageAttachment;
import com.javadiscord.jdi.core.models.message.MessageReaction;
import com.javadiscord.jdi.core.models.message.embed.Embed;
import com.javadiscord.jdi.core.models.user.User;
import com.javadiscord.jdi.internal.api.channel.*;

import java.util.List;
import java.util.Optional;

public class ChannelRequest {
    private final DiscordResponseParser responseParser;
    private final long guildId;

    public ChannelRequest(DiscordResponseParser responseParser, long guildId) {
        this.responseParser = responseParser;
        this.guildId = guildId;
    }

    public AsyncResponse<ThreadMember> addThreadMember(long channelId, long userId) {
        return responseParser.callAndParse(
                ThreadMember.class, new AddThreadMemberRequest(channelId, userId));
    }

    public AsyncResponse<Void> bulkDeleteMessages(long channelId, List<Long> messageIds) {
        return responseParser.callAndParse(
                Void.class, new BulkDeleteMessagesRequest(channelId, messageIds));
    }

    public AsyncResponse<Invite> createInvite(
            long channelId, int maxAge, int maxUses, boolean temporary) {
        return responseParser.callAndParse(
                Invite.class,
                new ChannelCreateInviteRequest(channelId, maxAge, maxUses, temporary));
    }

    public AsyncResponse<Message> createMessage(CreateMessageBuilder builder) {
        return responseParser.callAndParse(Message.class, builder.build());
    }

    public AsyncResponse<MessageReaction> createReaction(
            long channelId, long messageId, String emoji) {
        return responseParser.callAndParse(
                MessageReaction.class, new CreateReactionRequest(channelId, messageId, emoji));
    }

    public AsyncResponse<Message> crossPostMessage(long channelId, long messageId) {
        return responseParser.callAndParse(
                Message.class, new CrossPostMessageRequest(channelId, messageId));
    }

    public AsyncResponse<Void> deleteAllReactionsForEmoji(
            long channelId, long messageId, String emoji) {
        return responseParser.callAndParse(
                Void.class, new DeleteAllReactionsForEmojiRequest(channelId, messageId, emoji));
    }

    public AsyncResponse<Void> deleteAllReactions(long channelId, long messageId) {
        return responseParser.callAndParse(
                Void.class, new DeleteAllReactionsRequest(channelId, messageId));
    }

    public AsyncResponse<Channel> deleteChannel(long channelId, long overwriteId) {
        return responseParser.callAndParse(
                Channel.class, new DeleteChannelRequest(channelId, overwriteId));
    }

    public AsyncResponse<Message> deleteMessage(long channelId, long messageId) {
        return responseParser.callAndParse(
                Message.class, new DeleteMessageRequest(channelId, messageId));
    }

    public AsyncResponse<Void> deleteOwnReaction(long channelId, long messageId, String emoji) {
        return responseParser.callAndParse(
                Void.class, new DeleteOwnReactionRequest(channelId, messageId, emoji));
    }

    public AsyncResponse<Void> deleteUserReaction(
            long channelId, long messageId, String emoji, long userId) {
        return responseParser.callAndParse(
                Void.class, new DeleteUserReactionRequest(channelId, messageId, emoji, userId));
    }

    public AsyncResponse<Void> editChannelPermissions(EditChannelPermissionsBuilder builder) {
        return responseParser.callAndParse(Void.class, builder.build());
    }

    public AsyncResponse<Message> editMessage(
            long channelId,
            long messageId,
            String content,
            List<Embed> embeds,
            int flags,
            List<ChannelMention> allowedMentions,
            List<Object> components,
            List<Object> files,
            List<MessageAttachment> attachments) {
        return responseParser.callAndParse(
                Message.class,
                new EditMessageRequest(
                        channelId,
                        messageId,
                        content,
                        embeds,
                        flags,
                        allowedMentions,
                        components,
                        files,
                        attachments));
    }

    public AsyncResponse<Message> fetchChannelMessage(long channelId, long messageId) {
        return responseParser.callAndParse(
                Message.class, new FetchChannelMessageRequest(channelId, messageId));
    }

    public AsyncResponse<List<Message>> fetchChannelMessages(FetchChannelMessagesBuilder builder) {
        return responseParser.callAndParseList(Message.class, builder.build());
    }

    public AsyncResponse<Channel> fetchChannel(long channelId) {
        return responseParser.callAndParse(Channel.class, new FetchChannelRequest(channelId));
    }

    public AsyncResponse<List<User>> fetchUserReactionsToMessage(
            FetchUserReactionsToMessageBuilder builder) {
        return responseParser.callAndParseList(User.class, builder.build());
    }

    public AsyncResponse<Channel> followAnnouncementChannel(long channelId) {
        return responseParser.callAndParse(
                Channel.class, new FollowAnnouncementChannelRequest(channelId));
    }

    public AsyncResponse<List<Invite>> channelInvites(long channelId) {
        return responseParser.callAndParseList(
                Invite.class, new GetChannelInvitesRequest(channelId));
    }

    public AsyncResponse<List<Message>> channelPinnedMessages(long channelId) {
        return responseParser.callAndParseList(
                Message.class, new GetPinnedMessagesRequest(channelId));
    }

    public AsyncResponse<ThreadMember> getThreadMember(long channelId, long userId) {
        return responseParser.callAndParse(
                ThreadMember.class,
                new GetThreadMemberRequest(channelId, userId, Optional.of(true)));
    }

    public AsyncResponse<Void> groupDMAddRecipient(
            long channelId, long userId, String accessToken, String nickname) {
        return responseParser.callAndParse(
                Void.class,
                new GroupDMAddRecipientRequest(channelId, userId, accessToken, nickname));
    }

    public AsyncResponse<Void> groupDMRemoveRecipient(long channelId, long userId) {
        return responseParser.callAndParse(
                Void.class, new GroupDMRemoveRecipientRequest(channelId, userId));
    }

    public AsyncResponse<Channel> joinThread(long channelId) {
        return responseParser.callAndParse(Channel.class, new JoinThreadRequest(channelId));
    }

    public AsyncResponse<Channel> leaveThread(long channelId) {
        return responseParser.callAndParse(Channel.class, new LeaveThreadRequest(channelId));
    }

    public AsyncResponse<List<Channel>> listJoinedPrivateArchivedThreads(
            ListJoinedPrivateArchivedThreadsBuilder builder) {
        return responseParser.callAndParseList(Channel.class, builder.build());
    }

    public AsyncResponse<List<Channel>> listPrivateArchivedThreads(
            ListPrivateArchivedThreadsBuilder builder) {
        return responseParser.callAndParseList(Channel.class, builder.build());
    }

    public AsyncResponse<List<Channel>> listPublicArchivedThreads(
            ListPublicArchivedThreadsBuilder builder) {
        return responseParser.callAndParseList(Channel.class, builder.build());
    }

    public AsyncResponse<List<ThreadMember>> listThreadMembers(ListThreadMembersBuilder builder) {
        return responseParser.callAndParseList(ThreadMember.class, builder.build());
    }

    public AsyncResponse<Channel> modifyChannel(
            long channelId, String name, String base64EncodedIcon) {
        return responseParser.callAndParse(
                Channel.class, new ModifyChannelRequest(channelId, name, base64EncodedIcon));
    }

    public AsyncResponse<Void> pinMessage(long channelId, long messageId) {
        return responseParser.callAndParse(Void.class, new PinMessageRequest(channelId, messageId));
    }

    public AsyncResponse<ThreadMember> removeThreadMember(long channelId, long userId) {
        return responseParser.callAndParse(
                ThreadMember.class, new RemoveThreadMemberRequest(channelId, userId));
    }

    public AsyncResponse<Channel> startThreadFromMessage(StartThreadFromMessageBuilder builder) {
        return responseParser.callAndParse(Channel.class, builder.build());
    }

    public AsyncResponse<Channel> startThreadInForumOrMediaChannel(
            StartThreadInForumOrMediaChannelBuilder builder) {
        return responseParser.callAndParse(Channel.class, builder.build());
    }

    public AsyncResponse<Channel> startThreadWithoutMessage(
            StartThreadWithoutMessageBuilder builder) {
        return responseParser.callAndParse(Channel.class, builder.build());
    }

    public AsyncResponse<Void> typingIndicatorRequest(long channelId) {
        return responseParser.callAndParse(
                Void.class, new TriggerTypingIndicatorRequest(channelId));
    }

    public AsyncResponse<Void> unpinMessage(long channelId, long messageId) {
        return responseParser.callAndParse(
                Void.class, new UnpinMessageRequest(channelId, messageId));
    }
}
