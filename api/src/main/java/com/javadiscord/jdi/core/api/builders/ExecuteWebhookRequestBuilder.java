package com.javadiscord.jdi.core.api.builders;

import java.util.List;
import java.util.Optional;

import com.javadiscord.jdi.core.models.message.MessageAttachment;
import com.javadiscord.jdi.core.models.message.embed.Embed;
import com.javadiscord.jdi.core.models.poll.Poll;
import com.javadiscord.jdi.internal.api.webhook.ExecuteWebhookRequest;

public final class ExecuteWebhookRequestBuilder {
    private final long webhookId;
    private final String webhookToken;
    private final String payloadJson;
    private Optional<Boolean> waits;
    private Optional<Long> threadId;
    private Optional<String> content;
    private Optional<String> username;
    private Optional<String> avatarUrl;
    private Optional<Boolean> tts;
    private Optional<List<Embed>> embeds;
    private Optional<Object> allowedMentions;
    private Optional<List<Object>> components;
    private Optional<Object> files;
    private Optional<List<MessageAttachment>> attachments;
    private Optional<Integer> flags;
    private Optional<String> threadName;
    private Optional<List<Long>> appliedTags;
    private Optional<Poll> poll;

    public ExecuteWebhookRequestBuilder(long webhookId, String webhookToken, String payloadJson) {
        this.webhookId = webhookId;
        this.webhookToken = webhookToken;
        this.payloadJson = payloadJson;
        this.waits = Optional.empty();
        this.threadId = Optional.empty();
        this.content = Optional.empty();
        this.username = Optional.empty();
        this.avatarUrl = Optional.empty();
        this.tts = Optional.empty();
        this.embeds = Optional.empty();
        this.allowedMentions = Optional.empty();
        this.components = Optional.empty();
        this.files = Optional.empty();
        this.attachments = Optional.empty();
        this.flags = Optional.empty();
        this.threadName = Optional.empty();
        this.appliedTags = Optional.empty();
        this.poll = Optional.empty();
    }

    public ExecuteWebhookRequestBuilder waits(boolean waits) {
        this.waits = Optional.of(waits);
        return this;
    }

    public ExecuteWebhookRequestBuilder threadId(long threadId) {
        this.threadId = Optional.of(threadId);
        return this;
    }

    public ExecuteWebhookRequestBuilder content(String content) {
        this.content = Optional.of(content);
        return this;
    }

    public ExecuteWebhookRequestBuilder username(String username) {
        this.username = Optional.of(username);
        return this;
    }

    public ExecuteWebhookRequestBuilder avatarUrl(String avatarUrl) {
        this.avatarUrl = Optional.of(avatarUrl);
        return this;
    }

    public ExecuteWebhookRequestBuilder tts(boolean tts) {
        this.tts = Optional.of(tts);
        return this;
    }

    public ExecuteWebhookRequestBuilder embeds(List<Embed> embeds) {
        this.embeds = Optional.of(embeds);
        return this;
    }

    public ExecuteWebhookRequestBuilder allowedMentions(Object allowedMentions) {
        this.allowedMentions = Optional.of(allowedMentions);
        return this;
    }

    public ExecuteWebhookRequestBuilder components(List<Object> components) {
        this.components = Optional.of(components);
        return this;
    }

    public ExecuteWebhookRequestBuilder files(Object files) {
        this.files = Optional.of(files);
        return this;
    }

    public ExecuteWebhookRequestBuilder attachments(List<MessageAttachment> attachments) {
        this.attachments = Optional.of(attachments);
        return this;
    }

    public ExecuteWebhookRequestBuilder flags(int flags) {
        this.flags = Optional.of(flags);
        return this;
    }

    public ExecuteWebhookRequestBuilder threadName(String threadName) {
        this.threadName = Optional.of(threadName);
        return this;
    }

    public ExecuteWebhookRequestBuilder appliedTags(List<Long> appliedTags) {
        this.appliedTags = Optional.of(appliedTags);
        return this;
    }

    public ExecuteWebhookRequestBuilder poll(Poll poll) {
        this.poll = Optional.of(poll);
        return this;
    }

    public ExecuteWebhookRequest build() {
        return new ExecuteWebhookRequest(
                webhookId,
                webhookToken,
                waits,
                threadId,
                content,
                username,
                avatarUrl,
                tts,
                embeds,
                allowedMentions,
                components,
                files,
                payloadJson,
                attachments,
                flags,
                threadName,
                appliedTags,
                poll
        );
    }
}
