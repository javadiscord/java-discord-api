package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.core.models.channel.ChannelMention;
import com.javadiscord.jdi.core.models.message.MessageAttachment;
import com.javadiscord.jdi.core.models.message.MessageReference;
import com.javadiscord.jdi.core.models.message.embed.Embed;
import com.javadiscord.jdi.internal.api.channel.CreateMessageRequest;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreateMessageBuilder {
    private final long channelId;
    private Optional<String> content;
    private Optional<Integer> nonce;
    private Optional<Boolean> tts;
    private Optional<List<Embed>> embeds;
    private Optional<List<ChannelMention>> mentions;
    private Optional<List<MessageReference>> messageReferences;
    private Optional<List<Integer>> components;
    private Optional<List<Long>> stickerIds;
    private Optional<List<Path>> files;
    private Optional<String> payloadJson;
    private List<MessageAttachment> attachments;
    private Optional<Integer> flags;
    private Optional<Boolean> enforceNonce;

    public CreateMessageBuilder(long channelId) {
        this.channelId = channelId;
        this.content = Optional.empty();
        this.nonce = Optional.empty();
        this.tts = Optional.empty();
        this.embeds = Optional.empty();
        this.mentions = Optional.empty();
        this.messageReferences = Optional.empty();
        this.components = Optional.empty();
        this.stickerIds = Optional.empty();
        this.files = Optional.empty();
        this.payloadJson = Optional.empty();
        this.attachments = new ArrayList<>();
        this.flags = Optional.empty();
        this.enforceNonce = Optional.empty();
    }

    public CreateMessageBuilder content(String content) {
        this.content = Optional.of(content);
        return this;
    }

    public CreateMessageBuilder nonce(int nonce) {
        this.nonce = Optional.of(nonce);
        return this;
    }

    public CreateMessageBuilder tts(boolean tts) {
        this.tts = Optional.of(tts);
        return this;
    }

    public CreateMessageBuilder embeds(List<Embed> embeds) {
        this.embeds = Optional.of(embeds);
        return this;
    }

    public CreateMessageBuilder mentions(List<ChannelMention> mentions) {
        this.mentions = Optional.of(mentions);
        return this;
    }

    public CreateMessageBuilder messageReferences(List<MessageReference> messageReferences) {
        this.messageReferences = Optional.of(messageReferences);
        return this;
    }

    public CreateMessageBuilder components(List<Integer> components) {
        this.components = Optional.of(components);
        return this;
    }

    public CreateMessageBuilder stickerIds(List<Long> stickerIds) {
        this.stickerIds = Optional.of(stickerIds);
        return this;
    }

    public CreateMessageBuilder files(List<Path> files) {
        this.files = Optional.of(files);
        return this;
    }

    public CreateMessageBuilder payloadJson(String payloadJson) {
        this.payloadJson = Optional.of(payloadJson);
        return this;
    }

    public CreateMessageBuilder attachments(List<MessageAttachment> attachments) {
        this.attachments = attachments;
        return this;
    }

    public CreateMessageBuilder flags(int flags) {
        this.flags = Optional.of(flags);
        return this;
    }

    public CreateMessageBuilder enforceNonce(boolean enforceNonce) {
        this.enforceNonce = Optional.of(enforceNonce);
        return this;
    }

    public CreateMessageRequest build() {
        return new CreateMessageRequest(
                channelId,
                content,
                nonce,
                tts,
                embeds,
                mentions,
                messageReferences,
                components,
                stickerIds,
                files,
                payloadJson,
                attachments,
                flags,
                enforceNonce);
    }
}
