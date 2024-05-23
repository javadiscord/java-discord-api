package com.javadiscord.jdi.internal.api.channel;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.core.models.channel.ChannelMention;
import com.javadiscord.jdi.core.models.message.MessageAttachment;
import com.javadiscord.jdi.core.models.message.MessageReference;
import com.javadiscord.jdi.core.models.message.embed.Embed;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import com.github.mizosoft.methanol.MultipartBodyPublisher;

public record CreateMessageRequest(
    long channelId,
    Optional<String> content,
    Optional<Integer> nonce,
    Optional<Boolean> tts,
    Optional<List<Embed>> embeds,
    Optional<List<ChannelMention>> mentions,
    Optional<List<MessageReference>> messageReferences,
    Optional<List<Integer>> components,
    Optional<List<Long>> stickerIds,
    Optional<List<Path>> files,
    Optional<String> payloadJson,
    List<MessageAttachment> attachments,
    Optional<Integer> flags,
    Optional<Boolean> enforceNonce
) implements DiscordRequest {

    /** TODO: Add functionality for the enforceNonce flag */
    @Override
    public DiscordRequestBuilder create() {
        MultipartBodyPublisher.Builder bodyBuilder = MultipartBodyPublisher.newBuilder();

        Map<String, Object> body = new HashMap<>();
        content.ifPresent(val -> body.put("content", val));
        nonce.ifPresent(val -> body.put("nonce", val));
        tts.ifPresent(val -> body.put("tts", val));
        embeds.ifPresent(val -> body.put("embeds", val));
        mentions.ifPresent(val -> body.put("allowed_mentions", val));
        messageReferences.ifPresent(val -> body.put("message_reference", val));
        components.ifPresent(val -> body.put("components", val));
        stickerIds.ifPresent(val -> body.put("sticker_ids", val));
        flags.ifPresent(val -> body.put("flags", val));
        enforceNonce.ifPresent(val -> body.put("enforce_nonce", val));

        if (payloadJson.isPresent()) {

            bodyBuilder.textPart("payload_json", body);

            return new DiscordRequestBuilder()
                .post()
                .path("/channels/%s/messages".formatted(channelId))
                .multipartBody(bodyBuilder.build());
        }

        return new DiscordRequestBuilder()
            .post()
            .path("/channels/%s/messages".formatted(channelId))
            .body(body);
    }
}
