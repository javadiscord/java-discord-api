package com.javadiscord;

import com.javadiscord.api.RequestRunner;
import com.javadiscord.api.channel.Channel;

public class Discord {
    private final Channel channel;

    protected Discord(RequestRunner requestRunner) {
        this.channel = new Channel(requestRunner);
    }

    public Channel getChannel() {
        return channel;
    }
}
