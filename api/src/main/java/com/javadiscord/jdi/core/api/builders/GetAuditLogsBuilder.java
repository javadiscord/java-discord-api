package com.javadiscord.jdi.core.api.builders;

import java.util.Optional;

import com.javadiscord.jdi.internal.api.audit_logs.GetGuildAuditLogRequest;

public class GetAuditLogsBuilder {
    private long guildId;
    private Optional<Long> userId;
    private Optional<Integer> actionType;
    private Optional<Long> before;
    private Optional<Long> after;
    private Optional<Integer> limit;
    private Optional<String> reason;

    public GetAuditLogsBuilder() {
        this.userId = Optional.empty();
        this.actionType = Optional.empty();
        this.before = Optional.empty();
        this.after = Optional.empty();
        this.limit = Optional.empty();
        this.reason = Optional.empty();
    }

    public GetAuditLogsBuilder userId(long userId) {
        this.userId = Optional.of(userId);
        return this;
    }

    public GetAuditLogsBuilder actionType(Integer actionType) {
        this.actionType = Optional.of(actionType);
        return this;
    }

    public GetAuditLogsBuilder before(Long before) {
        this.before = Optional.of(before);
        return this;
    }

    public GetAuditLogsBuilder after(Long after) {
        this.after = Optional.of(after);
        return this;
    }

    public GetAuditLogsBuilder limit(Integer limit) {
        this.limit = Optional.of(limit);
        return this;
    }

    public GetAuditLogsBuilder reason(String reason) {
        this.reason = Optional.of(reason);
        return this;
    }

    public GetGuildAuditLogRequest build() {
        return new GetGuildAuditLogRequest(
            guildId,
            userId,
            actionType,
            before,
            after,
            limit,
            reason
        );
    }

    public GetAuditLogsBuilder guildId(long guildId) {
        this.guildId = guildId;
        return this;
    }
}
