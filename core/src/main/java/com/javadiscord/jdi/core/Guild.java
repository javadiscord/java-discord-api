package com.javadiscord.jdi.core;

import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.request.AuditLogsRequest;
import com.javadiscord.jdi.internal.request.ChannelRequest;
import com.javadiscord.jdi.internal.request.builders.CreateMessageBuilder;
import com.javadiscord.jdi.internal.response.DiscordResponseParser;

public class Guild {
    private final com.javadiscord.jdi.core.models.guild.Guild metadata;
    private final Cache cache;
    private final Discord discord;
    private final ChannelRequest channelRequest;
    private final AuditLogsRequest auditLogsRequest;

    public Guild(com.javadiscord.jdi.core.models.guild.Guild guild, Cache cache, Discord discord) {
        this.metadata = guild;
        this.cache = cache;
        this.discord = discord;

        DiscordResponseParser discordResponseParser =
                new DiscordResponseParser(discord.getDiscordRequestDispatcher());

        this.channelRequest = new ChannelRequest(discordResponseParser);
        this.auditLogsRequest = new AuditLogsRequest(discordResponseParser);
    }

    public ChannelRequest channel() {
        return channelRequest;
    }

    public AuditLogsRequest auditLogs() {
        return auditLogsRequest;
    }

    public void sendMessage(CreateMessageBuilder builder) {
        channelRequest.createMessage(builder);
    }

    public com.javadiscord.jdi.core.models.guild.Guild getMetadata() {
        return metadata;
    }

    public Cache getCache() {
        return cache;
    }
}
