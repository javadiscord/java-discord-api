package com.javadiscord.jdi.internal.request;

import com.javadiscord.jdi.core.models.audit_log.AuditLogEntry;
import com.javadiscord.jdi.internal.request.builders.GetAuditLogsBuilder;
import com.javadiscord.jdi.internal.response.AsyncResponse;
import com.javadiscord.jdi.internal.response.DiscordResponseParser;

import java.util.List;

public class AuditLogsRequest {
    private final DiscordResponseParser responseParser;

    public AuditLogsRequest(DiscordResponseParser responseParser) {
        this.responseParser = responseParser;
    }

    public AsyncResponse<List<AuditLogEntry>> getAuditLogs(GetAuditLogsBuilder builder) {
        return responseParser.callAndParse(builder.build());
    }
}
