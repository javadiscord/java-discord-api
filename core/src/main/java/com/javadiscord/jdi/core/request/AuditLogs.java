package com.javadiscord.jdi.core.request;

import com.javadiscord.jdi.core.request.builders.GetAuditLogsBuilder;
import com.javadiscord.jdi.internal.api.audit_logs.GetGuildAuditLogRequest;

public class AuditLogs {

    public GetGuildAuditLogRequest getAuditLogs(GetAuditLogsBuilder builder) {
        return builder.build();
    }
}
