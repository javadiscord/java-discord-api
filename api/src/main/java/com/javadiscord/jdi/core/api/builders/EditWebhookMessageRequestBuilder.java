package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.core.models.message.MessageAttachment;
import com.javadiscord.jdi.core.models.message.embed.Embed;
import com.javadiscord.jdi.internal.api.webhook.EditWebhookMessageRequest;

import java.util.List;
import java.util.Optional;

public final class EditWebhookMessageRequestBuilder {
    private final long webhookId;
    private final String webhookToken;
    private final long messageId;
    private Optional<Long> threadId;
    private Optional<String> content;
    private Optional<List<Embed>> embeds;
    private Optional<Object> allowedMentions;
    private Optional<List<Object>> components;
    private Optional<Object> files;
    private Optional<String> payloadJson;
    private Optional<List<MessageAttachment>> attachments;

    public EditWebhookMessageRequestBuilder(long webhookId, String webhookToken, long messageId) {
        this.webhookId = webhookId;
        this.webhookToken = webhookToken;
        this.messageId = messageId;
        this.threadId = Optional.empty();
        this.content = Optional.empty();
        this.embeds = Optional.empty();
        this.allowedMentions = Optional.empty();
        this.components = Optional.empty();
        this.files = Optional.empty();
        this.payloadJson = Optional.empty();
        this.attachments = Optional.empty();
    }

    public EditWebhookMessageRequestBuilder threadId(long threadId) {
        this.threadId = Optional.of(threadId);
        return this;
    }

    public EditWebhookMessageRequestBuilder content(String content) {
        this.content = Optional.of(content);
        return this;
    }

    public EditWebhookMessageRequestBuilder embeds(List<Embed> embeds) {
        this.embeds = Optional.of(embeds);
        return this;
    }

    public EditWebhookMessageRequestBuilder allowedMentions(Object allowedMentions) {
        this.allowedMentions = Optional.of(allowedMentions);
        return this;
    }

    public EditWebhookMessageRequestBuilder components(List<Object> components) {
        this.components = Optional.of(components);
        return this;
    }

    public EditWebhookMessageRequestBuilder files(Object files) {
        this.files = Optional.of(files);
        return this;
    }

    public EditWebhookMessageRequestBuilder payloadJson(String payloadJson) {
        this.payloadJson = Optional.of(payloadJson);
        return this;
    }

    public EditWebhookMessageRequestBuilder attachments(List<MessageAttachment> attachments) {
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
                this.payloadJson,
                this.attachments);
    }
}
