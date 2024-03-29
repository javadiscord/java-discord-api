package com.javadiscord.jdi.internal.api.channel;

import com.javadiscord.jdi.internal.api.Future;
import com.javadiscord.jdi.internal.api.HTTPRequest;
import com.javadiscord.jdi.internal.api.HTTPResponse;
import com.javadiscord.jdi.internal.api.RequestRunner;

public class Channel {
    private static final String URI = "/channels/%s/messages";
    private final RequestRunner requestRunner;

    public Channel(RequestRunner requestRunner) {
        this.requestRunner = requestRunner;
    }

    public Future<HTTPResponse> sendMessage(String channelId, String message) {
        return requestRunner.queue(
                new HTTPRequest(
                        "POST",
                        URI.formatted(channelId),
                        "{ \"content\": \"%s\" }".formatted(message)));
    }
}
