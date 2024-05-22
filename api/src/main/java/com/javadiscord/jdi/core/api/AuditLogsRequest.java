package com.javadiscord.jdi.core.api;

import java.util.List;

import com.javadiscord.jdi.core.api.builders.GetAuditLogsBuilder;
import com.javadiscord.jdi.core.models.audit_log.AuditLogEntry;

public class AuditLogsRequest {
    private final DiscordResponseParser responseParser;
    private final long guildId;

    public AuditLogsRequest(DiscordResponseParser responseParser, long guildId) {
        this.responseParser = responseParser;
        this.guildId = guildId;
    }

    public AsyncResponse<List<AuditLogEntry>> getAuditLogs(GetAuditLogsBuilder builder) {
        return responseParser.callAndParseList(
            AuditLogEntry.class, builder.guildId(guildId).build()
        );
    }
}
