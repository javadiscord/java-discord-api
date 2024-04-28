package com.javadiscord.jdi.internal.request;

import com.javadiscord.jdi.internal.api.audit_logs.GetGuildAuditLogRequest;
import com.javadiscord.jdi.internal.request.builders.GetAuditLogsBuilder;

public class AuditLogs {

    public GetGuildAuditLogRequest getAuditLogs(GetAuditLogsBuilder builder) {
        return builder.build();
    }
}
