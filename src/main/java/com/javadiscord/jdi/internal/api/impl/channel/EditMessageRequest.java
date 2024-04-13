package com.javadiscord.jdi.internal.api.impl.channel;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;
import com.javadiscord.jdi.internal.models.channel.ChannelMention;
import com.javadiscord.jdi.internal.models.message.MessageAttachment;
import com.javadiscord.jdi.internal.models.message.embed.Embed;

import java.util.List;
import java.util.Map;

public record EditMessageRequest(
        long channelId,
        long messageId,
        String content,
        List<Embed> embeds,
        int flags,
        List<ChannelMention> allowedMentions,
        List<Object> components, // TODO: Create components object
        List<Object> files,
        String payloadJson,
        List<MessageAttachment> attachments)
        implements DiscordRequest {

    // TODO: Fix sending files/attachments
    @Override
    public DiscordRequestBuilder create() {
        return new DiscordRequestBuilder()
                .patch()
                .path("/channels/%s/messages/%s".formatted(channelId, messageId))
                .body(
                        Map.of(
                                "content", content,
                                "embeds", embeds,
                                "flats", flags,
                                "allowed_mentions", allowedMentions,
                                "components", components,
                                "files", files,
                                "payload_json", payloadJson,
                                "attachments", attachments))
                .putHeader(
                        "Content-Type",
                        files.isEmpty() || attachments.isEmpty()
                                ? "application/json"
                                : "multipart/form-data");
    }
}
