package com.javadiscord.jdi.internal.api.channel;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.javadiscord.jdi.core.api.utils.DiscordImageUtil;
import com.javadiscord.jdi.core.models.channel.ChannelMention;
import com.javadiscord.jdi.core.models.message.MessageAttachment;
import com.javadiscord.jdi.core.models.message.MessageReference;
import com.javadiscord.jdi.core.models.message.embed.Embed;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import com.github.mizosoft.methanol.MediaType;
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
    Optional<List<MessageAttachment>> attachments,
    Optional<Integer> flags,
    Optional<Boolean> enforceNonce
) implements DiscordRequest {

    /** TODO: Add functionality for the enforceNonce flag */
    @Override
    public DiscordRequestBuilder create() {
        MultipartBodyPublisher.Builder multiPartBody = MultipartBodyPublisher.newBuilder();

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

        attachments.ifPresent(val -> body.put("attachments", val));

        enforceNonce.ifPresent(val -> body.put("enforce_nonce", val));

        if (files.isPresent()) {
            List<Path> f = files.get();
            for (int i = 0; i < f.size(); i++) {
                try {
                    Path path = f.get(i);
                    String name = "files[" + i + "]";

                    String extension = DiscordImageUtil.getExtension(path);

                    switch (extension) {
                        case "png" -> multiPartBody.filePart(name, path, MediaType.IMAGE_PNG);
                        case "jpg", "jpeg" ->
                            multiPartBody.filePart(name, path, MediaType.IMAGE_JPEG);
                        case "gif" -> multiPartBody.filePart(name, path, MediaType.IMAGE_GIF);
                        default -> multiPartBody.filePart(name, path, MediaType.ANY);
                    }

                } catch (FileNotFoundException ignore) {
                    /* Ignore */
                }
            }

            body.forEach(multiPartBody::textPart);

            return new DiscordRequestBuilder()
                .post()
                .path("/channels/%s/messages".formatted(channelId))
                .multipartBody(multiPartBody.build());
        }

        return new DiscordRequestBuilder()
            .post()
            .path("/channels/%s/messages".formatted(channelId))
            .body(body);
    }
}
