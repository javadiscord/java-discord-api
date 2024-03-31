package com.javadiscord.jdi.internal.api.actions;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javadiscord.jdi.internal.api.DiscordRequest;
import com.javadiscord.jdi.internal.api.DiscordResponse;
import com.javadiscord.jdi.internal.api.RequestRunner;
import com.javadiscord.jdi.internal.api.Response;
import com.javadiscord.jdi.internal.api.HttpMethod;

public final class Channel {
    private final RequestRunner requestRunner;

    public Channel(RequestRunner requestRunner) {
        this.requestRunner = requestRunner;
    }

    public Response<DiscordResponse> sendMessage(String channelId, String message) throws JsonProcessingException {
        DiscordRequest sendMessageRequest = new DiscordRequest(
            HttpMethod.POST,
            "/channels/%s/messages".formatted(channelId),
            null,
            Map.of("content", message)
        );

        return requestRunner.queue(sendMessageRequest);
    }

    public Response<DiscordResponse> editMessage(String channelId, String messageId, String message) throws JsonProcessingException {
        DiscordRequest editMessageRequest = new DiscordRequest(
            HttpMethod.POST,
            "/channels/%s/messages/%s".formatted(channelId, messageId),
            null,
            Map.of("content", message)
        );

        return requestRunner.queue(editMessageRequest);
    }

    public Response<DiscordResponse> deleteMessage(String channelId, String messageId) {
        DiscordRequest deleteMessageRequest = new DiscordRequest(
            HttpMethod.DELETE,
            "/channels/%s/messages/%s".formatted(channelId, messageId)
        );

        return requestRunner.queue(deleteMessageRequest);
    }

    public Response<DiscordResponse> addReaction(String channelId, String messageId, String emoji) {
        DiscordRequest addReactionRequest = new DiscordRequest(
            HttpMethod.POST,
            "/channels/%s/messages/%s/reactions/%s/@me".formatted(channelId, messageId, emoji)
        );

        return requestRunner.queue(addReactionRequest);
    }

    //TODO: getMessages
    //TODO: getReactions
    //TODO: uploadFile
    //TODO: pinMessage
    //TODO: unpinMessage
    //TODO: getMessageReactions
    //TODO: addReactionToMessage
    //TODO: removeReactionFromMessage

    //TODO: getChannel
    //TODO: getChannelMessages
    //TODO: editChannel
    //TODO: deleteChannel
    //TODO: getChannelInvites
}
