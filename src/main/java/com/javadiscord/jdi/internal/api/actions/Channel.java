package com.javadiscord.jdi.internal.api.actions;

import com.javadiscord.jdi.internal.api.Future;
import com.javadiscord.jdi.internal.api.HTTPRequest;
import com.javadiscord.jdi.internal.api.HTTPResponse;
import com.javadiscord.jdi.internal.api.RequestRunner;

public class Channel {
    private final RequestRunner requestRunner;

    public Channel(RequestRunner requestRunner) {
        this.requestRunner = requestRunner;
    }

    public Future<HTTPResponse> sendMessage(String channelId, String message) {
        return requestRunner.queue(
                new HTTPRequest(
                        "POST",
                        "/channels/%s/messages".formatted(channelId),
                        "{ \"content\": \"%s\" }".formatted(message)));
    }

    public Future<HTTPResponse> editMessage(String channelId, String messageId, String message) {
        return requestRunner.queue(
                new HTTPRequest(
                        "POST",
                        "/channels/%s/messages/%s".formatted(channelId, messageId),
                        "{ \"content\": \"%s\" }".formatted(message)));
    }

    public Future<HTTPResponse> deleteMessage(String channelId, String messageId) {
        return requestRunner.queue(
                new HTTPRequest(
                        "DELETE",
                        "/channels/%s/messages/%s".formatted(channelId, messageId)));
    }

    public Future<HTTPResponse> addReaction(String channelId, String messageId, String emoji) {
        return requestRunner.queue(
                new HTTPRequest(
                        "POST",
                        "/channels/%s/messages/%s/reactions/%s/@me".formatted(channelId, messageId, emoji)));
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
