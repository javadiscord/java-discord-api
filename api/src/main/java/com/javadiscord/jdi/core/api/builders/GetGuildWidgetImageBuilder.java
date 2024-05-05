package com.javadiscord.jdi.core.api.builders;

import com.javadiscord.jdi.core.models.guild.WidgetStyleOptions;
import com.javadiscord.jdi.internal.api.guild.GetGuildWidgetImageRequest;

import java.util.Optional;

public final class GetGuildWidgetImageBuilder {
    private long guildId;
    private Optional<WidgetStyleOptions> style;

    public GetGuildWidgetImageBuilder() {
        this.style = Optional.empty();
    }

    public GetGuildWidgetImageBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }

    public GetGuildWidgetImageBuilder style(WidgetStyleOptions style) {
        this.style = Optional.of(style);
        return this;
    }

    public GetGuildWidgetImageRequest build() {
        return new GetGuildWidgetImageRequest(guildId, style);
    }
}
