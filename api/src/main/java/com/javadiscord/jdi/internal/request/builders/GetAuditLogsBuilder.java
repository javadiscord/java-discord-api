package com.javadiscord.jdi.internal.request.builders;

import com.javadiscord.jdi.internal.api.audit_logs.GetGuildAuditLogRequest;

import java.util.Optional;

public class GetAuditLogsBuilder {
    private final long guildId;
    private Optional<Long> userId;
    private Optional<Integer> actionType;
    private Optional<Long> before;
    private Optional<Long> after;
    private Optional<Integer> limit;
    private Optional<String> reason;

    public GetAuditLogsBuilder(long guildId) {
        this.guildId = guildId;
        this.userId = Optional.empty();
        this.actionType = Optional.empty();
        this.before = Optional.empty();
        this.after = Optional.empty();
        this.limit = Optional.empty();
        this.reason = Optional.empty();
    }

    public GetAuditLogsBuilder setUserId(long userId) {
        this.userId = Optional.of(userId);
        return this;
    }

    public GetAuditLogsBuilder setActionType(Integer actionType) {
        this.actionType = Optional.of(actionType);
        return this;
    }

    public GetAuditLogsBuilder setBefore(Long before) {
        this.before = Optional.of(before);
        return this;
    }

    public GetAuditLogsBuilder setAfter(Long after) {
        this.after = Optional.of(after);
        return this;
    }

    public GetAuditLogsBuilder setLimit(Integer limit) {
        this.limit = Optional.of(limit);
        return this;
    }

    public GetAuditLogsBuilder setReason(String reason) {
        this.reason = Optional.of(reason);
        return this;
    }

    public GetGuildAuditLogRequest build() {
        return new GetGuildAuditLogRequest(
                guildId, userId, actionType, before, after, limit, reason);
    }
}
