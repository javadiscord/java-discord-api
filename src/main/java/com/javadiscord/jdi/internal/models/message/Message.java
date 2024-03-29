package com.javadiscord.jdi.internal.models.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.jdi.internal.gateway.handlers.events.codec.handlers.ready.Application;
import com.javadiscord.jdi.internal.models.channel.Channel;
import com.javadiscord.jdi.internal.models.channel.ChannelMention;
import com.javadiscord.jdi.internal.models.guild.ResolvedData;
import com.javadiscord.jdi.internal.models.guild.RoleSubscriptionData;
import com.javadiscord.jdi.internal.models.message.embed.Embed;
import com.javadiscord.jdi.internal.models.user.User;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Message(
        @JsonProperty("id") long id,
        @JsonProperty("channel_id") long channelId,
        @JsonProperty("author") User author,
        @JsonProperty("content") String content,
        @JsonProperty("timestamp") String timestamp,
        @JsonProperty("edited_timestamp") String editedTimestamp,
        @JsonProperty("tts") boolean tts,
        @JsonProperty("mention_everyone") boolean mentionEveryone,
        @JsonProperty("mentions") List<User> mentions,
        @JsonProperty("mention_roles") List<String> mentionRoles,
        @JsonProperty("mention_channels") List<ChannelMention> mentionChannels,
        @JsonProperty("attachments") List<MessageAttachment> messageAttachments,
        @JsonProperty("embeds") List<Embed> embeds,
        @JsonProperty("reactions") List<MessageReaction> messageReactions,
        @JsonProperty("nonce") Object nonce,
        @JsonProperty("pinned") boolean pinned,
        @JsonProperty("webhook_id") long webhookId,
        @JsonProperty("type") int type,
        @JsonProperty("activity") MessageActivity activity,
        @JsonProperty("application") Application application,
        @JsonProperty("application_id") String applicationId,
        @JsonProperty("message_reference") MessageReference messageReference,
        @JsonProperty("flags") int flags,
        @JsonProperty("referenced_message") Message referencedMessage,
        @JsonProperty("interaction_metadata") MessageInteractionMetadata interactionMetadata,
        @JsonProperty("interaction") MessageInteraction interaction,
        @JsonProperty("thread") Channel thread,
        //  @JsonProperty("components") List<MessageComponent> components,
        @JsonProperty("sticker_items") List<StickerItem> stickerItems,
        @JsonProperty("stickers") List<Sticker> stickers,
        @JsonProperty("position") int position,
        @JsonProperty("role_subscription_data") RoleSubscriptionData roleSubscriptionData,
        @JsonProperty("resolved") ResolvedData resolved) {}
