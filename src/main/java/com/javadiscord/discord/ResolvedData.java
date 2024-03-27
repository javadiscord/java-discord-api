package com.javadiscord.discord;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.javadiscord.discord.channel.Channel;
import com.javadiscord.discord.message.Message;
import com.javadiscord.discord.message.MessageAttachment;
import com.javadiscord.discord.user.Member;
import com.javadiscord.discord.user.User;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResolvedData(
        @JsonProperty("users") Map<String, User> users,
        @JsonProperty("members") Map<String, Member> members,
        @JsonProperty("roles") Map<String, Role> roles,
        @JsonProperty("channels") Map<String, Channel> channels,
        @JsonProperty("messages") Map<String, Message> messages,
        @JsonProperty("attachments") Map<String, MessageAttachment> attachments) {}
