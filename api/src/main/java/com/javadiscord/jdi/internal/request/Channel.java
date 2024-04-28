package com.javadiscord.jdi.internal.request;

import com.javadiscord.jdi.core.models.channel.ChannelMention;
import com.javadiscord.jdi.core.models.message.MessageAttachment;
import com.javadiscord.jdi.core.models.message.embed.Embed;
import com.javadiscord.jdi.core.request.builders.*;
import com.javadiscord.jdi.internal.api.channel.*;
import com.javadiscord.jdi.internal.api.request.builders.*;
import com.javadiscord.jdi.internal.request.builders.*;

import java.util.List;
import java.util.Optional;

public class Channel {

    public AddThreadMemberRequest addThreadMember(long channelId, long userId) {
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

    public DeleteAllReactionsRequest deleteAllReactions(long channelId, long messageId) {
        return new DeleteAllReactionsRequest(channelId, messageId);
    }

    public DeleteChannelRequest deleteChannel(long channelId, long overwriteId) {
        return new DeleteChannelRequest(channelId, overwriteId);
    }

    public DeleteMessageRequest deleteMessage(long channelId, long messageId) {
        return new DeleteMessageRequest(channelId, messageId);
    }

    public DeleteOwnReactionRequest deleteOwnReaction(
            long channelId, long messageId, String emoji) {
        return new DeleteOwnReactionRequest(channelId, messageId, emoji);
    }

    public DeleteUserReactionRequest deleteUserReaction(
            long channelId, long messageId, String emoji, long userId) {
        return new DeleteUserReactionRequest(channelId, messageId, emoji, userId);
    }

    public EditChannelPermissionsRequest editChannelPermissions(
            EditChannelPermissionsBuilder builder) {
        return builder.build();
    }

    public EditMessageRequest editMessage(
            long channelId,
            long messageId,
            String content,
            List<Embed> embeds,
            int flags,
            List<ChannelMention> allowedMentions,
            List<Object> components,
            List<Object> files,
            List<MessageAttachment> attachments) {
        return new EditMessageRequest(
                channelId,
                messageId,
                content,
                embeds,
                flags,
                allowedMentions,
                components,
                files,
                attachments);
    }

    public FetchChannelMessageRequest fetchChannelMessage(long channelId, long messageId) {
        return new FetchChannelMessageRequest(channelId, messageId);
    }

    public FetchChannelMessagesRequest fetchChannelMessages(FetchChannelMessagesBuilder builder) {
        return builder.build();
    }

    public FetchChannelRequest fetchChannel(long channelId) {
        return new FetchChannelRequest(channelId);
    }

    public FetchUserReactionsToMessageRequest fetchUserReactionsToMessage(
            FetchUserReactionsToMessageBuilder builder) {
        return builder.build();
    }

    public FollowAnnouncementChannelRequest followAnnouncementChannel(long channelId) {
        return new FollowAnnouncementChannelRequest(channelId);
    }

    public GetChannelInvitesRequest channelInvites(long channelId) {
        return new GetChannelInvitesRequest(channelId);
    }

    public GetPinnedMessagesRequest channelPinnedMessages(long channelId) {
        return new GetPinnedMessagesRequest(channelId);
    }

    public GetThreadMemberRequest getThreadMember(long channelId, long userId) {
        return new GetThreadMemberRequest(channelId, userId, Optional.of(true));
    }

    public GroupDMAddRecipientRequest groupDMAddRecipient(
            long channelId, long userId, String accessToken, String nickname) {
        return new GroupDMAddRecipientRequest(channelId, userId, accessToken, nickname);
    }

    public GroupDMRemoveRecipientRequest groupDMRemoveRecipient(long channelId, long userId) {
        return new GroupDMRemoveRecipientRequest(channelId, userId);
    }

    public JoinThreadRequest joinThread(long channelId) {
        return new JoinThreadRequest(channelId);
    }

    public LeaveThreadRequest leaveThread(long channelId) {
        return new LeaveThreadRequest(channelId);
    }

    public ListJoinedPrivateArchivedThreadsRequest listJoinedPrivateArchivedThreads(
            ListJoinedPrivateArchivedThreadsBuilder builder) {
        return builder.build();
    }

    public ListPrivateArchivedThreadsRequest listPrivateArchivedThreads(
            ListPrivateArchivedThreadsBuilder builder) {
        return builder.build();
    }

    public ListPublicArchivedThreadsRequest listPublicArchivedThreads(
            ListPublicArchivedThreadsBuilder builder) {
        return builder.build();
    }

    public ListThreadMembersRequest listThreadMembers(ListThreadMembersBuilder builder) {
        return builder.build();
    }

    public ModifyChannelRequest modifyChannel(
            long channelId, String name, String base64EncodedIcon) {
        return new ModifyChannelRequest(channelId, name, base64EncodedIcon);
    }

    public PinMessageRequest pinMessage(long channelId, long messageId) {
        return new PinMessageRequest(channelId, messageId);
    }

    public RemoveThreadMemberRequest removeThreadMember(long channelId, long userId) {
        return new RemoveThreadMemberRequest(channelId, userId);
    }

    public StartThreadFromMessageRequest startThreadFromMessage(
            StartThreadFromMessageBuilder builder) {
        return builder.build();
    }

    public StartThreadInForumOrMediaChannelRequest startThreadInForumOrMediaChannel(
            StartThreadInForumOrMediaChannelBuilder builder) {
        return builder.build();
    }

    public StartThreadWithoutMessageRequest startThreadWithoutMessage(
            StartThreadWithoutMessageBuilder builder) {
        return builder.build();
    }

    public TriggerTypingIndicatorRequest typingIndicatorRequest(long channelId) {
        return new TriggerTypingIndicatorRequest(channelId);
    }

    public UnpinMessageRequest unpinMessage(long channelId, long messageId) {
        return new UnpinMessageRequest(channelId, messageId);
    }
}
