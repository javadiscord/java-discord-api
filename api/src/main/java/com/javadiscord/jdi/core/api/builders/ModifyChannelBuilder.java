package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.channel.ModifyChannelRequest;

public class ModifyChannelBuilder {
    private final long channelId;
    private Optional<String> name;
    private Optional<String> base64EncodedIcon;

    public ModifyChannelBuilder(long channelId) {
        this.channelId = channelId;
        this.name = Optional.empty();
        this.base64EncodedIcon = Optional.empty();
    }

    public ModifyChannelBuilder name(String name) {
        this.name = Optional.of(name);
        return this;
    }

    public ModifyChannelBuilder base64EncodedIcon(String base64EncodedIcon) {
        this.base64EncodedIcon = Optional.of(base64EncodedIcon);
        return this;
    }

    public ModifyChannelRequest build() {
        return new ModifyChannelRequest(channelId, name, base64EncodedIcon);
    }
}
