package com.javadiscord.jdi.internal.api.impl.channel;

import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;
import com.javadiscord.jdi.internal.models.channel.ChannelMention;
import com.javadiscord.jdi.internal.models.message.MessageAttachment;
import com.javadiscord.jdi.internal.models.message.MessageReference;
import com.javadiscord.jdi.internal.models.message.embed.Embed;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public record CreateMessageRequest(
        long channelId,
        Optional<String> content,
        Optional<Integer> nonce,
        Optional<Boolean> tts,
        Optional<List<Embed>> embeds,
        Optional<List<ChannelMention>> mentions,
        Optional<List<MessageReference>> messageReferences,
        Optional<List<Object>> components, // TODO: Create Components object
        Optional<List<Long>> stickerIds,
        Optional<Object> files, // TODO: Create File object
        Optional<String> payloadJson,
        List<MessageAttachment> attachments,
        Optional<Integer> flags,
        Optional<Boolean> enforceNonce)
        implements DiscordRequest {

    /** TODO: Add functionality for the enforceNonce flag */
    @Override
    public DiscordRequestBuilder create() {
        Map<String, Object> body = new HashMap<>();
        content.ifPresent(val -> body.put("content", val));
        content.ifPresent(val -> body.put("nonce", val));
        content.ifPresent(val -> body.put("tts", val));
        content.ifPresent(val -> body.put("embeds", val));
        content.ifPresent(val -> body.put("allowed_mentions", val));
        content.ifPresent(val -> body.put("message_reference", val));
        content.ifPresent(val -> body.put("components", val));
        content.ifPresent(val -> body.put("sticker_ids", val));
        content.ifPresent(val -> body.put("files", val));
        content.ifPresent(val -> body.put("payload_json", val));
        content.ifPresent(val -> body.put("flags", val));
        content.ifPresent(val -> body.put("enforce_nonce", val));
        return new DiscordRequestBuilder()
                .post()
                .path("/channels/%s/messages".formatted(channelId))
                .body(body);
    }
}
