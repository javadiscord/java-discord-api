package com.javadiscord.jdi.internal.models.guild;

import java.util.Map;

import com.javadiscord.jdi.internal.models.channel.Channel;
import com.javadiscord.jdi.internal.models.message.Message;
import com.javadiscord.jdi.internal.models.message.MessageAttachment;
import com.javadiscord.jdi.internal.models.user.Member;
import com.javadiscord.jdi.internal.models.user.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResolvedData(@JsonProperty("users") Map<String, User> users,
        @JsonProperty("members") Map<String, Member> members,
        @JsonProperty("roles") Map<String, Role> roles,
        @JsonProperty("channels") Map<String, Channel> channels,
        @JsonProperty("messages") Map<String, Message> messages,
        @JsonProperty("attachments") Map<String, MessageAttachment> attachments) {}
