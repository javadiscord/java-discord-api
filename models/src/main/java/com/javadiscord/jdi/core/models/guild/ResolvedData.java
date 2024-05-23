package com.javadiscord.jdi.core.models.guild;

import java.util.Map;

import com.javadiscord.jdi.core.models.channel.Channel;
import com.javadiscord.jdi.core.models.message.Message;
import com.javadiscord.jdi.core.models.message.MessageAttachment;
import com.javadiscord.jdi.core.models.user.Member;
import com.javadiscord.jdi.core.models.user.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResolvedData(
    @JsonProperty("users") Map<String, User> users,
    @JsonProperty("members") Map<String, Member> members,
    @JsonProperty("roles") Map<String, Role> roles,
    @JsonProperty("channels") Map<String, Channel> channels,
    @JsonProperty("messages") Map<String, Message> messages,
    @JsonProperty("attachments") Map<String, MessageAttachment> attachments
) {}
