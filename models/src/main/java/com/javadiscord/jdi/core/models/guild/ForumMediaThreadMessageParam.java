package com.javadiscord.jdi.core.models.guild;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.jdi.core.models.message.MessageAttachment;
import com.javadiscord.jdi.core.models.message.embed.Embed;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ForumMediaThreadMessageParam(
        @JsonProperty("content") String content,
        @JsonProperty("embeds") List<Embed> embeds,
        @JsonProperty("allowed_mentions") List<AllowedMentions> allowedMentions,
        @JsonProperty("components") List<Object> components,
        @JsonProperty("sticker_ids") List<Long> stickerIds,
        @JsonProperty("attachments") List<MessageAttachment> attachments,
        @JsonProperty("flags") int flags) {}
