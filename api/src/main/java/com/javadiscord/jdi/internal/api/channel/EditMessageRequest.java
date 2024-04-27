package com.javadiscord.jdi.internal.api.channel;

import com.github.mizosoft.methanol.MultipartBodyPublisher;
import com.javadiscord.jdi.core.models.channel.ChannelMention;
import com.javadiscord.jdi.core.models.message.MessageAttachment;
import com.javadiscord.jdi.core.models.message.embed.Embed;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.util.List;

public record EditMessageRequest(
        long channelId,
        long messageId,
        String content,
        List<Embed> embeds,
        int flags,
        List<ChannelMention> allowedMentions,
        List<Object> components, // TODO: Create components object
        List<Object> files,
        List<MessageAttachment> attachments)
        implements DiscordRequest {

    // TODO: Fix sending files/attachments
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .patch()
                .path("/channels/%s/messages/%s".formatted(channelId, messageId))
                .multipartBody(
                        MultipartBodyPublisher.newBuilder()
                                .textPart("content", content)
                                .textPart("embeds", embeds)
                                .textPart("flats", flags)
                                .textPart("allowed_mentions", allowedMentions)
                                .textPart("components", components)
                                .textPart("files", files)
                                .textPart("attachments", attachments)
                                .build());
    }
}
