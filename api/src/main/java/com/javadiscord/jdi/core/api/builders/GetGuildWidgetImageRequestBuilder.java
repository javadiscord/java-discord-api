package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.internal.api.guild.GetGuildWidgetImageRequest;

import java.util.Optional;

public final class GetGuildWidgetImageRequestBuilder {
    private long guildId;
    private Optional<String> style;

    public GetGuildWidgetImageRequestBuilder() {
        this.style = Optional.empty();
    }

    public GetGuildWidgetImageRequestBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public GetGuildWidgetImageRequestBuilder style(String style) {
        this.style = Optional.of(style);
        return this;
    }

    public GetGuildWidgetImageRequest build() {
        return new GetGuildWidgetImageRequest(guildId, style);
    }
}
