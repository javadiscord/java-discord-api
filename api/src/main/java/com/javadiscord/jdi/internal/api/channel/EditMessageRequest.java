package com.javadiscord.jdi.internal.api.channel;

import com.github.mizosoft.methanol.MultipartBodyPublisher;
import com.javadiscord.jdi.core.models.channel.ChannelMention;
import com.javadiscord.jdi.core.models.message.MessageAttachment;
import com.javadiscord.jdi.core.models.message.embed.Embed;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordRequestBuilder;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public record EditMessageRequest(
        long channelId,
        long messageId,
        Optional<String> content,
        Optional<List<Embed>> embeds,
        Optional<Integer> flags,
        Optional<List<ChannelMention>> allowedMentions,
        Optional<List<Integer>> components,
        Optional<List<Path>> files,
        Optional<List<MessageAttachment>> attachments)
        implements DiscordRequest {

    // TODO: Fix sending files/attachments
    @Override
    public DiscordRequestBuilder create() {
        MultipartBodyPublisher.Builder bodyBuilder = MultipartBodyPublisher.newBuilder();

        Map<String, Object> body = new HashMap<>();
        content.ifPresent(val -> body.put("content", val));
        embeds.ifPresent(val -> body.put("embeds", val));
        flags.ifPresent(val -> body.put("flags", val));
        allowedMentions.ifPresent(val -> body.put("allowed_mentions", val));
        components.ifPresent(val -> body.put("components", val));
        files.ifPresent(val -> body.put("files", val));
        attachments.ifPresent(val -> body.put("attachments", val));

        bodyBuilder.textPart("payload_json", body);

        files.ifPresent(
                paths -> {
                    for (int i = 0; i < paths.size(); i++) {
                        try {
                            bodyBuilder.filePart("file[%d]".formatted(i), paths.get(i));
                        } catch (FileNotFoundException ignored) {
                        }
                    }
                });

        return new DiscordRequestBuilder()
                .patch()
                .path("/channels/%s/messages/%s".formatted(channelId, messageId))
                .multipartBody(bodyBuilder.build());
    }
}
