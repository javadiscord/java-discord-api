package com.javadiscord.jdi.core.api.builders;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import com.javadiscord.jdi.core.models.channel.ChannelMention;
import com.javadiscord.jdi.core.models.message.MessageAttachment;
import com.javadiscord.jdi.core.models.message.embed.Embed;
import com.javadiscord.jdi.internal.api.channel.EditMessageRequest;

public class EditMessageBuilder {
    private final long channelId;
    private final long messageId;
    private Optional<String> content;
    private Optional<List<Embed>> embeds;
    private Optional<Integer> flags;
    private Optional<List<ChannelMention>> allowedMentions;
    private Optional<List<Integer>> components;
    private Optional<List<Path>> files;
    private Optional<List<MessageAttachment>> attachments;

    public EditMessageBuilder(long channelId, long messageId) {
        this.channelId = channelId;
        this.messageId = messageId;
        this.content = Optional.empty();
        this.embeds = Optional.empty();
        this.flags = Optional.empty();
        this.allowedMentions = Optional.empty();
        this.components = Optional.empty();
        this.files = Optional.empty();
        this.attachments = Optional.empty();
    }

    public EditMessageBuilder content(String content) {
        this.content = Optional.of(content);
        return this;
    }

    public EditMessageBuilder embeds(List<Embed> embeds) {
        this.embeds = Optional.of(embeds);
        return this;
    }

    public EditMessageBuilder flags(int flags) {
        this.flags = Optional.of(flags);
        return this;
    }

    public EditMessageBuilder allowedMentions(List<ChannelMention> allowedMentions) {
        this.allowedMentions = Optional.of(allowedMentions);
        return this;
    }

    public EditMessageBuilder components(List<Integer> components) {
        this.components = Optional.of(components);
        return this;
    }

    public EditMessageBuilder files(List<Path> files) {
        this.files = Optional.of(files);
        return this;
    }

    public EditMessageBuilder attachments(List<MessageAttachment> attachments) {
        this.attachments = Optional.of(attachments);
        return this;
    }

    public EditMessageRequest build() {
        return new EditMessageRequest(
            channelId,
            messageId,
            content,
            embeds,
            flags,
            allowedMentions,
            components,
            files,
            attachments
        );
    }
}
