package com.javadiscord.jdi.core.api.builders.command;

import java.util.List;

import com.javadiscord.jdi.core.models.channel.AllowedMentions;
import com.javadiscord.jdi.core.models.channel.Attachment;
import com.javadiscord.jdi.core.models.message.Component;
import com.javadiscord.jdi.core.models.message.embed.Embed;
import com.javadiscord.jdi.core.models.poll.Poll;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CallbackMessage {
    @JsonProperty("tts")
    private boolean tts;

    @JsonProperty("content")
    private String content;

    @JsonProperty("embeds")
    private List<Embed> embeds;

    @JsonProperty("allowed_mentions")
    private AllowedMentions allowedMentions;

    @JsonProperty("flags")
    private int flags;

    @JsonProperty("components")
    private List<Component> components;

    @JsonProperty("attachments")
    private List<Attachment> attachments;

    @JsonProperty("poll")
    private Poll poll;

    public boolean isTts() {
        return tts;
    }

    public void setTts(boolean tts) {
        this.tts = tts;
    }

    public String getContent() {
        return content;
    }

    public CallbackMessage setContent(String content) {
        this.content = content;
        return this;
    }

    public List<Embed> getEmbeds() {
        return embeds;
    }

    public CallbackMessage setEmbeds(List<Embed> embeds) {
        this.embeds = embeds;
        return this;
    }

    public AllowedMentions getAllowedMentions() {
        return allowedMentions;
    }

    public CallbackMessage setAllowedMentions(AllowedMentions allowedMentions) {
        this.allowedMentions = allowedMentions;
        return this;
    }

    public int getFlags() {
        return flags;
    }

    public CallbackMessage setFlags(int flags) {
        this.flags = flags;
        return this;
    }

    public List<Component> getComponents() {
        return components;
    }

    public CallbackMessage setComponents(List<Component> components) {
        this.components = components;
        return this;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public CallbackMessage setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
        return this;
    }

    public Poll getPoll() {
        return poll;
    }

    public CallbackMessage setPoll(Poll poll) {
        this.poll = poll;
        return this;
    }
}
