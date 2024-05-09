package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.channel.EditChannelPermissionsRequest;

import java.util.Optional;

public class EditChannelPermissionsBuilder {
    private final long channelId;
    private final long overwriteId;
    private final int type;
    private Optional<String> allow;
    private Optional<String> deny;

    public EditChannelPermissionsBuilder(long channelId, long overwriteId, int type) {
        this.channelId = channelId;
        this.overwriteId = overwriteId;
        this.type = type;
        this.allow = Optional.empty();
        this.deny = Optional.empty();
    }

    public EditChannelPermissionsBuilder allow(String allow) {
        this.allow = Optional.of(allow);
        return this;
    }

    public EditChannelPermissionsBuilder deny(String deny) {
        this.deny = Optional.of(deny);
        return this;
    }

    public EditChannelPermissionsRequest build() {
        return new EditChannelPermissionsRequest(channelId, overwriteId, allow, deny, type);
    }
}
