package com.javadiscord.jdi.core.api.builders;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import com.javadiscord.jdi.core.models.channel.AllowedMentions;
import com.javadiscord.jdi.core.models.message.Component;
import com.javadiscord.jdi.core.models.message.MessageAttachment;
import com.javadiscord.jdi.core.models.message.embed.Embed;
import com.javadiscord.jdi.internal.api.webhook.EditWebhookMessageRequest;

public final class EditWebhookMessageBuilder {
    private final long webhookId;
    private final String webhookToken;
    private final long messageId;
    private Optional<Long> threadId;
    private Optional<String> content;
    private Optional<List<Embed>> embeds;
    private Optional<AllowedMentions> allowedMentions;
    private Optional<List<Component>> components;
    private Optional<List<Path>> files;
    private Optional<List<MessageAttachment>> attachments;

    public EditWebhookMessageBuilder(long webhookId, String webhookToken, long messageId) {
        this.webhookId = webhookId;
        this.webhookToken = webhookToken;
        this.messageId = messageId;
        this.threadId = Optional.empty();
        this.content = Optional.empty();
        this.embeds = Optional.empty();
        this.allowedMentions = Optional.empty();
        this.components = Optional.empty();
        this.files = Optional.empty();
        this.attachments = Optional.empty();
    }

    public EditWebhookMessageBuilder threadId(long threadId) {
        this.threadId = Optional.of(threadId);
        return this;
    }

    public EditWebhookMessageBuilder content(String content) {
        this.content = Optional.of(content);
        return this;
    }

    public EditWebhookMessageBuilder embeds(List<Embed> embeds) {
        this.embeds = Optional.of(embeds);
        return this;
    }

    public EditWebhookMessageBuilder allowedMentions(AllowedMentions allowedMentions) {
        this.allowedMentions = Optional.of(allowedMentions);
        return this;
    }

    public EditWebhookMessageBuilder components(List<Component> components) {
        this.components = Optional.of(components);
        return this;
    }

    public EditWebhookMessageBuilder files(List<Path> files) {
        this.files = Optional.of(files);
        return this;
    }

    public EditWebhookMessageBuilder attachments(List<MessageAttachment> attachments) {
        this.attachments = Optional.of(attachments);
        return this;
    }

    public EditWebhookMessageRequest build() {
        return new EditWebhookMessageRequest(
            this.webhookId,
            this.webhookToken,
            this.messageId,
            this.threadId,
            this.content,
            this.embeds,
            this.allowedMentions,
            this.components,
            this.files,
            this.attachments
        );
    }
}
