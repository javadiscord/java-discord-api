package com.javadiscord.jdi.core.api.builders;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import com.javadiscord.jdi.core.models.channel.AllowedMentions;
import com.javadiscord.jdi.core.models.message.MessageAttachment;
import com.javadiscord.jdi.core.models.message.embed.Embed;
import com.javadiscord.jdi.core.models.poll.Poll;
import com.javadiscord.jdi.internal.api.webhook.ExecuteWebhookRequest;

public final class ExecuteWebhookBuilder {
    private final long webhookId;
    private final String webhookToken;
    private Optional<Boolean> waits;
    private Optional<Long> threadId;
    private Optional<String> content;
    private Optional<String> username;
    private Optional<String> avatarUrl;
    private Optional<Boolean> tts;
    private Optional<List<Embed>> embeds;
    private Optional<AllowedMentions> allowedMentions;
    private Optional<List<Integer>> components;
    private Optional<List<Path>> files;
    private Optional<List<MessageAttachment>> attachments;
    private Optional<Integer> flags;
    private Optional<String> threadName;
    private Optional<List<Long>> appliedTags;
    private Optional<Poll> poll;

    public ExecuteWebhookBuilder(long webhookId, String webhookToken) {
        this.webhookId = webhookId;
        this.webhookToken = webhookToken;
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

    public ExecuteWebhookBuilder waits(boolean waits) {
        this.waits = Optional.of(waits);
        return this;
    }

    public ExecuteWebhookBuilder threadId(long threadId) {
        this.threadId = Optional.of(threadId);
        return this;
    }

    public ExecuteWebhookBuilder content(String content) {
        this.content = Optional.of(content);
        return this;
    }

    public ExecuteWebhookBuilder username(String username) {
        this.username = Optional.of(username);
        return this;
    }

    public ExecuteWebhookBuilder avatarUrl(String avatarUrl) {
        this.avatarUrl = Optional.of(avatarUrl);
        return this;
    }

    public ExecuteWebhookBuilder tts(boolean tts) {
        this.tts = Optional.of(tts);
        return this;
    }

    public ExecuteWebhookBuilder embeds(List<Embed> embeds) {
        this.embeds = Optional.of(embeds);
        return this;
    }

    public ExecuteWebhookBuilder allowedMentions(AllowedMentions allowedMentions) {
        this.allowedMentions = Optional.of(allowedMentions);
        return this;
    }

    public ExecuteWebhookBuilder components(List<Integer> components) {
        this.components = Optional.of(components);
        return this;
    }

    public ExecuteWebhookBuilder files(List<Path> files) {
        this.files = Optional.of(files);
        return this;
    }

    public ExecuteWebhookBuilder attachments(List<MessageAttachment> attachments) {
        this.attachments = Optional.of(attachments);
        return this;
    }

    public ExecuteWebhookBuilder flags(Integer flags) {
        this.flags = Optional.of(flags);
        return this;
    }

    public ExecuteWebhookBuilder threadName(String threadName) {
        this.threadName = Optional.of(threadName);
        return this;
    }

    public ExecuteWebhookBuilder appliedTags(List<Long> appliedTags) {
        this.appliedTags = Optional.of(appliedTags);
        return this;
    }

    public ExecuteWebhookBuilder poll(Poll poll) {
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
            attachments,
            flags,
            threadName,
            appliedTags,
            poll
        );
    }
}
