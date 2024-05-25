package com.javadiscord.jdi.core.api;

import java.util.List;

import com.javadiscord.jdi.core.api.builders.CreateGuildFromTemplateBuilder;
import com.javadiscord.jdi.core.api.builders.CreateGuildTemplateBuilder;
import com.javadiscord.jdi.core.api.builders.ModifyGuildTemplateBuilder;
import com.javadiscord.jdi.core.models.guild.Guild;
import com.javadiscord.jdi.core.models.guild_template.GuildTemplate;
import com.javadiscord.jdi.internal.api.guild_template.*;

public class GuildTemplateRequest {
    private final DiscordResponseParser responseParser;
    private final long guildId;

    public GuildTemplateRequest(DiscordResponseParser responseParser, long guildId) {
        this.responseParser = responseParser;
        this.guildId = guildId;
    }

    public AsyncResponse<Guild> createGuildFromTemplate(CreateGuildFromTemplateBuilder builder) {
        return responseParser.callAndParse(
            Guild.class, builder.build()
        );
    }

    public AsyncResponse<GuildTemplate> createGuildTemplate(CreateGuildTemplateBuilder builder) {
        return responseParser.callAndParse(
            GuildTemplate.class, builder.guildId(guildId).build()
        );
    }

    public AsyncResponse<GuildTemplate> deleteGuildTemplate(String templateCode) {
        return responseParser.callAndParse(
            GuildTemplate.class, new DeleteGuildTemplateRequest(guildId, templateCode)
        );
    }

    public AsyncResponse<GuildTemplate> getGuildTemplate(String templateCode) {
        return responseParser
            .callAndParse(GuildTemplate.class, new GetGuildTemplateRequest(templateCode));
    }

    public AsyncResponse<List<GuildTemplate>> getGuildTemplates() {
        return responseParser
            .callAndParseList(GuildTemplate.class, new GetGuildTemplatesRequest(guildId));
    }

    public AsyncResponse<GuildTemplate> modifyGuildTemplate(ModifyGuildTemplateBuilder builder) {
        return responseParser.callAndParse(
            GuildTemplate.class, builder.guildId(guildId).build()
        );
    }

    public AsyncResponse<GuildTemplate> syncGuildTemplate(String templateCode) {
        return responseParser
            .callAndParse(GuildTemplate.class, new SyncGuildTemplateRequest(guildId, templateCode));
    }
}
