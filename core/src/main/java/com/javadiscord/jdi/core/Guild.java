package com.javadiscord.jdi.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javadiscord.jdi.internal.api.channel.FetchChannelRequest;
import com.javadiscord.jdi.internal.cache.Cache;
import com.javadiscord.jdi.internal.cache.CacheInterface;
import com.javadiscord.jdi.internal.models.channel.Channel;

import java.util.concurrent.CompletableFuture;

public class Guild {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final com.javadiscord.jdi.internal.models.guild.Guild metadata;
    private final CacheInterface<Object> cache;
    private final Discord discord;

    public Guild(
            com.javadiscord.jdi.internal.models.guild.Guild guild, Cache cache, Discord discord) {
        this.metadata = guild;
        this.cache = cache.getCacheForGuild(guild.id());
        this.discord = discord;
    }

    public Channel getChannel(long channelId) {
        CompletableFuture<Channel> future = new CompletableFuture<>();
        if (cache.isCached(channelId, Channel.class)) {
            future.complete((Channel) cache.get(channelId, Channel.class));
        } else {
            discord.sendRequest(new FetchChannelRequest(channelId))
                    .onSuccess(
                            res -> {
                                if (res.status() == 200) {
                                    try {
                                        Channel channel =
                                                OBJECT_MAPPER.readValue(res.body(), Channel.class);
                                        cache.add(channelId, channel);
                                        future.complete(channel);
                                    } catch (JsonProcessingException e) {
                                        future.completeExceptionally(e);
                                    }
                                } else {
                                    future.completeExceptionally(
                                            new RuntimeException(
                                                    """
                                    Failed to fetch channel %s, received status %s
                                    %s
                                    """
                                                            .formatted(
                                                                    channelId,
                                                                    res.status(),
                                                                    res.body())));
                                }
                            })
                    .onError(future::completeExceptionally);
        }
        try {
            return future.get();
        } catch (Exception e) {
            return null;
        }
    }

    public com.javadiscord.jdi.internal.models.guild.Guild getMetadata() {
        return metadata;
    }
}
